package br.com.clubedosbarbas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Cliente.Cliente;
import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosAtualizacaoCliente;
import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosCadastroCliente;
import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosListagemCliente;
import br.com.clubedosbarbas.api.domain.Cliente.repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCliente dados) {
        repository.save(new Cliente(dados));
    }

    @GetMapping
    public List<DadosListagemCliente> listar() {
        return repository.findAll().stream().map(DadosListagemCliente::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
