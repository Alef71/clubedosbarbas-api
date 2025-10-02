package br.com.clubedosbarbas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Estabelecimento.Estabelecimento;
import br.com.clubedosbarbas.api.domain.Estabelecimento.dto.DadosAtualizacaoEstabelecimento;
import br.com.clubedosbarbas.api.domain.Estabelecimento.dto.DadosCadastroEstabelecimento;
import br.com.clubedosbarbas.api.domain.Estabelecimento.dto.DadosListagemEstabelecimento;
import br.com.clubedosbarbas.api.domain.Estabelecimento.repository.EstabelecimentoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemEstabelecimento> cadastrar(@RequestBody @Valid DadosCadastroEstabelecimento dados) {
        if (repository.count() > 0) {
            return ResponseEntity.badRequest().build(); // Impede cadastrar mais de um
        }
        var estabelecimento = new Estabelecimento(null, dados.nome(), dados.horario_abertura(), dados.horario_fechamento());
        repository.save(estabelecimento);
        return ResponseEntity.ok(new DadosListagemEstabelecimento(estabelecimento));
    }

    @GetMapping
    public ResponseEntity<DadosListagemEstabelecimento> detalhar() {
        var estabelecimento = repository.findById(1L).orElse(null);
        if (estabelecimento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DadosListagemEstabelecimento(estabelecimento));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemEstabelecimento> atualizar(@RequestBody @Valid DadosAtualizacaoEstabelecimento dados) {
        var estabelecimento = repository.getReferenceById(1L); // Sempre atualiza o ID 1
        estabelecimento.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosListagemEstabelecimento(estabelecimento));
    }
}
