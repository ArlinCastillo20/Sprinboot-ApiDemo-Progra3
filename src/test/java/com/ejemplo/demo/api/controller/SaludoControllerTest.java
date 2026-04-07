package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.domain.service.SaludoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaludoController.class)
class SaludoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaludoService saludoService;

    @Test
    @DisplayName("Debe responder health correctamente")
    void debeResponderHealthCorrectamente() throws Exception {
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"))
                .andExpect(jsonPath("$.mensaje").value("Workshop Spring Boot activo"));
    }

    @Test
    @DisplayName("Debe responder saludo por GET")
    void debeResponderSaludoPorGet() throws Exception {
        when(saludoService.crearSaludo("Ana"))
                .thenReturn(new SaludoResponse("Hola, Ana. Bienvenido a Spring Boot 3!", Instant.now()));

        mockMvc.perform(get("/api/v1/saludos?nombre=Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Hola, Ana. Bienvenido a Spring Boot 3!"));
    }

    @Test
    @DisplayName("Debe responder saludo por POST")
    void debeResponderSaludoPorPost() throws Exception {
        when(saludoService.crearSaludo("Ana"))
                .thenReturn(new SaludoResponse("Hola, Ana. Bienvenido a Spring Boot 3!", Instant.now()));

        mockMvc.perform(post("/api/v1/saludos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Ana\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Hola, Ana. Bienvenido a Spring Boot 3!"));
    }

    @Test
    @DisplayName("Debe fallar validacion en POST")
    void debeFallarValidacionEnPost() throws Exception {
        mockMvc.perform(post("/api/v1/saludos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }
}