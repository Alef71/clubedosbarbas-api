package br.com.clubedosbarbas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosAtualizacaoCliente;
import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosCadastroCliente;
import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosListagemCliente;
import br.com.clubedosbarbas.api.domain.Cliente.repository.ClienteRepository;
import br.com.clubedosbarbas.api.domain.Cliente.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid DadosCadastroCliente dados) {
        clienteService.cadastrar(dados);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> listar() {
        var listaDto = repository.findAll().stream().map(DadosListagemCliente::new).toList();
        return ResponseEntity.ok(listaDto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Void> atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
