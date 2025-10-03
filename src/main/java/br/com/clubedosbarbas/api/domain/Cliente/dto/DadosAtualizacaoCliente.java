package br.com.clubedosbarbas.api.domain.Cliente.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String fotoUrl
) {}
