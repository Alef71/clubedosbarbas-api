package br.com.clubedosbarbas.api.domain.BloqueioHorario.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroBloqueio(
        @NotNull Long idBarbeiro,
         @NotNull LocalDateTime dataHoraInicio,
        @NotNull LocalDateTime dataHoraFim,   
        @NotBlank String motivo
) {}