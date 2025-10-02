package br.com.clubedosbarbas.api.domain.Barbeiro.dto;

import br.com.clubedosbarbas.api.domain.Barbeiro.Barbeiro;

public record DadosListagemBarbeiro(
    Long id,
    String nome,
    String username,
    String celular
) {
    public DadosListagemBarbeiro(Barbeiro barbeiro) {
        this(barbeiro.getId(), barbeiro.getNome(), barbeiro.getUsername(), barbeiro.getCelular());
    }
}
