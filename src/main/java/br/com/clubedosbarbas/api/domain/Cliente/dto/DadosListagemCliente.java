package br.com.clubedosbarbas.api.domain.Cliente.dto;

import br.com.clubedosbarbas.api.domain.Cliente.Cliente;

public record DadosListagemCliente(Long id, String nome, String telefone) {
    public DadosListagemCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone());
    }
}
