package br.com.clubedosbarbas.api.domain.Servico.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoServico(
        @NotNull
        Long id,
        String nome,
        String descricao,
        Integer duracaoEmMinutos, // Corrigido para camelCase
        Double valor
) {}