package br.com.clubedosbarbas.api.domain.Financeiro.dto;

import java.math.BigDecimal;

public record DadosRelatorioMensal(
        Integer ano,
        Integer mes,
        BigDecimal totalEntradas,
        BigDecimal totalDespesas,
        BigDecimal lucro
) {}