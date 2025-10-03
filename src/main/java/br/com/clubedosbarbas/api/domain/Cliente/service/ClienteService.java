package br.com.clubedosbarbas.api.domain.Cliente.service;

import org.springframework.stereotype.Service;

import br.com.clubedosbarbas.api.domain.Cliente.Cliente;
import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosCadastroCliente;
import br.com.clubedosbarbas.api.domain.Cliente.repository.ClienteRepository;
import br.com.clubedosbarbas.api.infra.exception.ValidacaoException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(DadosCadastroCliente dados) {
        if (clienteRepository.existsByTelefone(dados.telefone())) {
            throw new ValidacaoException("Já existe um cliente cadastrado com este número de telefone.");
        }

        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);
        return cliente;
    }
}