package br.com.clubedosbarbas.api.domain.Agendamento.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamento(
        @NotNull
        Long idBarbeiro,
        @NotNull
        Long idCliente,
        @NotEmpty
        List<Long> idsServicos,
        @NotNull @Future
        LocalDateTime dataHora
) {}