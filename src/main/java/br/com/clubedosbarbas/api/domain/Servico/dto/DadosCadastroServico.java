package br.com.clubedosbarbas.api.domain.Servico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroServico(
        @NotBlank
        String nome,
        String descricao,
        @NotNull
        Integer duracao_em_minutos,
        @NotNull
        Double valor
) {}
