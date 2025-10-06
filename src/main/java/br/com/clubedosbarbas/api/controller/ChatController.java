package br.com.clubedosbarbas.api.controller;

import br.com.clubedosbarbas.api.domain.Chat.Mensagem;
import br.com.clubedosbarbas.api.domain.Chat.StatusMensagem;
import br.com.clubedosbarbas.api.domain.Chat.dto.DadosEnvioMensagem;
import br.com.clubedosbarbas.api.domain.Chat.dto.DadosListagemMensagem;
import br.com.clubedosbarbas.api.domain.Chat.repository.MensagemRepository;
import br.com.clubedosbarbas.api.domain.Chat.repository.MensagemSpecifications;
import br.com.clubedosbarbas.api.infra.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    private MensagemRepository repository;

    @PostMapping("/mensagens")
    @Transactional
    public ResponseEntity<ApiResponse<DadosListagemMensagem>> enviar(@RequestBody @Valid DadosEnvioMensagem dados) {
        var mensagem = new Mensagem(null, dados.remetenteId(), dados.remetenteTipo(), dados.destinatarioId(), dados.destinatarioTipo(), dados.conteudo(), LocalDateTime.now(), StatusMensagem.ENVIADA);
        repository.save(mensagem);
        return ResponseEntity.ok(ApiResponse.success(new DadosListagemMensagem(mensagem)));
    }

    @GetMapping("/conversas")
    public ResponseEntity<ApiResponse<List<DadosListagemMensagem>>> getConversa(
            @RequestParam Long usuario1Id,
            @RequestParam Long usuario2Id,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim) {

        // Usa a nova classe Specification para construir a query dinamicamente
        Specification<Mensagem> spec = MensagemSpecifications.comFiltros(usuario1Id, usuario2Id, dataInicio, dataFim);
        
        var mensagens = repository.findAll(spec);
        
        var listaDto = mensagens.stream().map(DadosListagemMensagem::new).toList();
        return ResponseEntity.ok(ApiResponse.success(listaDto));
    }
}
