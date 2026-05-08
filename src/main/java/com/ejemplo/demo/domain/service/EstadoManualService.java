package com.ejemplo.demo.domain.service;

public class EstadoManualService {

    private int valorActual = 0;

    public int actualizar(int valor) {
        this.valorActual = valor;
        return this.valorActual;
    }

    public int obtener() {
        return this.valorActual;
    }
}