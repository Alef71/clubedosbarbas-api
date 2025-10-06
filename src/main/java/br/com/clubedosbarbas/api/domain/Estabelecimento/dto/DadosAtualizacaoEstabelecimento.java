package br.com.clubedosbarbas.api.domain.Estabelecimento.dto;

import java.time.LocalTime;

public record DadosAtualizacaoEstabelecimento(
        String nome,
        LocalTime horarioAbertura,
        LocalTime horarioFim
) {}