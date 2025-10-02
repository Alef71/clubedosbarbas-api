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

import br.com.clubedosbarbas.api.domain.Servico.Servico;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosAtualizacaoServico;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosCadastroServico;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosListagemServico;
import br.com.clubedosbarbas.api.domain.Servico.repository.ServicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroServico dados) {
        repository.save(new Servico(dados));
    }

    @GetMapping
    public List<DadosListagemServico> listar() {
        return repository.findAll().stream().map(DadosListagemServico::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoServico dados) {
        var servico = repository.getReferenceById(dados.id());
        servico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
