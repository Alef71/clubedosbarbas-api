package br.com.clubedosbarbas.api.domain.Servico.dto;

import br.com.clubedosbarbas.api.domain.Servico.Servico;

public record DadosListagemServico(
    Long id, 
    String nome, 
    Double valor, 
    Integer duracaoEmMinutos // Corrigido para camelCase
) {
    public DadosListagemServico(Servico servico) {
        // Chamada do método corrigida para camelCase
        this(servico.getId(), servico.getNome(), servico.getValor(), servico.getDuracaoEmMinutos());
    }
}