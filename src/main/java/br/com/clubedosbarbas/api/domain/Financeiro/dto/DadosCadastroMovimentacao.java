package br.com.clubedosbarbas.api.domain.Financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.clubedosbarbas.api.domain.Financeiro.TipoMovimentacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMovimentacao(
        @NotNull Long idBarbeiro,
        @NotNull LocalDate data,
        @NotNull TipoMovimentacao tipo,
        @NotBlank String descricao,
        @NotNull BigDecimal valor
) {}
