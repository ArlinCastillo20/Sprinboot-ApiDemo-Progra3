package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrestamoController.class)
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrestamoService prestamoService;

    @Test
    @DisplayName("Debe simular prestamo correctamente")
    void debeSimularPrestamoCorrectamente() throws Exception {
        when(prestamoService.simular(any()))
                .thenReturn(new PrestamoResponse(
                        new BigDecimal("888.49"),
                        new BigDecimal("661.88"),
                        new BigDecimal("10661.88")
                ));

        mockMvc.perform(post("/api/v1/simulaciones/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"monto\":10000,\"tasaAnual\":12,\"meses\":12}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuotaMensual").value(888.49))
                .andExpect(jsonPath("$.interesTotal").value(661.88))
                .andExpect(jsonPath("$.totalPagar").value(10661.88));
    }

    @Test
    @DisplayName("Debe fallar cuando el request es invalido")
    void debeFallarCuandoRequestEsInvalido() throws Exception {
        mockMvc.perform(post("/api/v1/simulaciones/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"monto\":0,\"tasaAnual\":12,\"meses\":12}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }
}