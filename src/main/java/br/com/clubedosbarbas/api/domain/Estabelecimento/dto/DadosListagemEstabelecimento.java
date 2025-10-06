package br.com.clubedosbarbas.api.domain.Estabelecimento.dto;

import java.time.LocalTime;

import br.com.clubedosbarbas.api.domain.Estabelecimento.Estabelecimento;

public record DadosListagemEstabelecimento(
    Long id, 
    String nome, 
    LocalTime horarioAbertura, 
    LocalTime horarioFim
) {
    public DadosListagemEstabelecimento(Estabelecimento estabelecimento) {
        this(estabelecimento.getId(), estabelecimento.getNome(), estabelecimento.getHorarioAbertura(), estabelecimento.getHorarioFim());
    }
}