package com.ejemplo.demo.domain.service;

import org.springframework.stereotype.Service;

@Service
public class EstadoSingletonService {

    private int valorActual = 0;

    public int actualizar(int valor) {
        this.valorActual = valor;
        return this.valorActual;
    }

    public int obtener() {
        return this.valorActual;
    }

    public int reset() {
        this.valorActual = 0;
        return this.valorActual;
    }
}