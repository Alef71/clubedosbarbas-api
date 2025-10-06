package br.com.clubedosbarbas.api.domain.Estabelecimento.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEstabelecimento(
        @NotBlank String nome,
        @NotNull LocalTime horarioAbertura,
        @NotNull LocalTime horarioFim
) {}