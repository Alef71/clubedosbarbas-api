package br.com.clubedosbarbas.api.domain.Servico.dto;

import br.com.clubedosbarbas.api.domain.Servico.Servico;

public record DadosListagemServico(Long id, String nome, Double valor, Integer duracao_em_minutos) {
    public DadosListagemServico(Servico servico) {
        this(servico.getId(), servico.getNome(), servico.getValor(), servico.getDuracao_em_minutos());
    }
}