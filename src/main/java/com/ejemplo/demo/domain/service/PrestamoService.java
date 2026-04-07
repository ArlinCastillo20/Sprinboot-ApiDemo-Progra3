package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class PrestamoService {

    public PrestamoResponse simular(PrestamoRequest request) {
        BigDecimal monto = request.monto();
        BigDecimal tasaAnual = request.tasaAnual();
        int meses = request.meses();

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }

        if (tasaAnual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tasa anual debe ser mayor a 0");
        }

        if (meses < 1 || meses > 360) {
            throw new IllegalArgumentException("Los meses deben estar entre 1 y 360");
        }

        MathContext mc = new MathContext(20, RoundingMode.HALF_UP);

        BigDecimal tasaMensual = tasaAnual
                .divide(BigDecimal.valueOf(12), mc)
                .divide(BigDecimal.valueOf(100), mc);

        BigDecimal unoMasR = BigDecimal.ONE.add(tasaMensual, mc);
        BigDecimal potencia = unoMasR.pow(meses, mc);

        BigDecimal numerador = monto.multiply(tasaMensual.multiply(potencia, mc), mc);
        BigDecimal denominador = potencia.subtract(BigDecimal.ONE, mc);

        BigDecimal cuotaMensual = numerador.divide(denominador, 2, RoundingMode.HALF_UP);
        BigDecimal totalPagar = cuotaMensual.multiply(BigDecimal.valueOf(meses)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interesTotal = totalPagar.subtract(monto).setScale(2, RoundingMode.HALF_UP);

        return new PrestamoResponse(cuotaMensual, interesTotal, totalPagar);
    }
}