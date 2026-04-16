package com.ejemplo.demo.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductoResponse(
        Long id,
        String nombre,
        String sku,
        BigDecimal precio,
        Integer stock,
        Long categoriaId,
        String categoriaNombre,
        Instant creadoEn
) {
}