package br.com.clubedosbarbas.api.domain.Estabelecimento.dto;

import java.time.LocalTime;

import br.com.clubedosbarbas.api.domain.Estabelecimento.Estabelecimento;

public record DadosListagemEstabelecimento(Long id, String nome, LocalTime horario_abertura, LocalTime horario_fechamento) {
    public DadosListagemEstabelecimento(Estabelecimento estabelecimento) {
        this(estabelecimento.getId(), estabelecimento.getNome(), estabelecimento.getHorario_abertura(), estabelecimento.getHorario_fechamento());
    }
}
