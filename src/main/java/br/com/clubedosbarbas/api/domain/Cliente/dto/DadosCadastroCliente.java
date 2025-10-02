package br.com.clubedosbarbas.api.domain.Cliente.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
        @NotBlank
        String nome,
        String telefone
) {}
