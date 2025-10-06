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

import br.com.clubedosbarbas.api.domain.Servico.Servico;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosAtualizacaoServico;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosCadastroServico;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosListagemServico;
import br.com.clubedosbarbas.api.domain.Servico.repository.ServicoRepository;
import br.com.clubedosbarbas.api.infra.dto.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ApiResponse<DadosListagemServico>> cadastrar(@RequestBody @Valid DadosCadastroServico dados) {
        var servico = new Servico(dados);
        repository.save(servico);
        return ResponseEntity.ok(ApiResponse.success(new DadosListagemServico(servico)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DadosListagemServico>>> listar() {
        var listaDto = repository.findAll().stream().map(DadosListagemServico::new).toList();
        return ResponseEntity.ok(ApiResponse.success(listaDto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ApiResponse<DadosListagemServico>> atualizar(@RequestBody @Valid DadosAtualizacaoServico dados) {
        var servico = repository.getReferenceById(dados.id());
        servico.atualizarInformacoes(dados);
        return ResponseEntity.ok(ApiResponse.success(new DadosListagemServico(servico)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}