package br.com.clubedosbarbas.api.domain.HorarioTrabalho.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroHorarioTrabalho(
        @NotNull Long idBarbeiro,
        @NotNull DayOfWeek diaDaSemana,
         @NotNull LocalTime horarioInicio, 
        @NotNull LocalTime horarioFim    
) {}
