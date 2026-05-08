package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.EstadoDemoResponse;
import com.ejemplo.demo.domain.service.EstadoManualService;
import com.ejemplo.demo.domain.service.EstadoSingletonService;
import com.ejemplo.demo.generated.api.DemoEstadoApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoEstadoController implements DemoEstadoApi {

    private final EstadoSingletonService estadoSingletonService;

    public DemoEstadoController(EstadoSingletonService estadoSingletonService) {
        this.estadoSingletonService = estadoSingletonService;
    }

    @Override
    public ResponseEntity<EstadoDemoResponse> actualizarSingleton(Integer valor) {
        int actual = estadoSingletonService.actualizar(valor);
        return ResponseEntity.ok(new EstadoDemoResponse("singleton", actual));
    }

    @Override
    public ResponseEntity<EstadoDemoResponse> obtenerSingleton() {
        return ResponseEntity.ok(new EstadoDemoResponse("singleton", estadoSingletonService.obtener()));
    }

    @Override
    public ResponseEntity<EstadoDemoResponse> reiniciarSingleton() {
        int actual = estadoSingletonService.reset();
        return ResponseEntity.ok(new EstadoDemoResponse("singleton", actual));
    }

    @Override
    public ResponseEntity<EstadoDemoResponse> actualizarManual(Integer valor) {
        EstadoManualService manual = new EstadoManualService();
        int actual = manual.actualizar(valor);
        return ResponseEntity.ok(new EstadoDemoResponse("manual", actual));
    }

    @Override
    public ResponseEntity<EstadoDemoResponse> obtenerManual() {
        EstadoManualService manual = new EstadoManualService();
        return ResponseEntity.ok(new EstadoDemoResponse("manual", manual.obtener()));
    }
}