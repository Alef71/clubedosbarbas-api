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

import br.com.clubedosbarbas.api.domain.Barbeiro.dto.DadosAtualizacaoBarbeiro;
import br.com.clubedosbarbas.api.domain.Barbeiro.dto.DadosCadastroBarbeiro;
import br.com.clubedosbarbas.api.domain.Barbeiro.dto.DadosListagemBarbeiro;
import br.com.clubedosbarbas.api.domain.Barbeiro.repository.BarbeiroRepository;
import br.com.clubedosbarbas.api.domain.Barbeiro.service.BarbeiroService;
import br.com.clubedosbarbas.api.infra.dto.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("barbeiros")
public class BarbeiroController {

    @Autowired
    private BarbeiroRepository repository;

    @Autowired
    private BarbeiroService barbeiroService; // Serviço que contém a lógica de negócio

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<DadosListagemBarbeiro>> cadastrar(@RequestBody @Valid DadosCadastroBarbeiro dados) {
        // CORREÇÃO: Agora usamos o serviço para cadastrar, que executa as validações
        var barbeiro = barbeiroService.cadastrar(dados);
        return ResponseEntity.ok(ApiResponse.success(new DadosListagemBarbeiro(barbeiro)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DadosListagemBarbeiro>>> listar() {
        var listaDto = repository.findAll().stream().map(DadosListagemBarbeiro::new).toList();
        return ResponseEntity.ok(ApiResponse.success(listaDto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ApiResponse<DadosListagemBarbeiro>> atualizar(@RequestBody @Valid DadosAtualizacaoBarbeiro dados) {
        var barbeiro = repository.getReferenceById(dados.id());
        barbeiro.atualizarInformacoes(dados);
        return ResponseEntity.ok(ApiResponse.success(new DadosListagemBarbeiro(barbeiro)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
