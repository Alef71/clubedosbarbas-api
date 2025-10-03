package br.com.clubedosbarbas.api.domain.Financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosRelatorioDiario(
        LocalDate data,
        BigDecimal totalEntradas,
        BigDecimal totalDespesas,
        BigDecimal lucro
) {}