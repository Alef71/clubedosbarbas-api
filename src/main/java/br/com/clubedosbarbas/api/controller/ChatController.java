package br.com.clubedosbarbas.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Chat.Mensagem;
import br.com.clubedosbarbas.api.domain.Chat.dto.DadosEnvioMensagem;
import br.com.clubedosbarbas.api.domain.Chat.dto.DadosListagemMensagem;
import br.com.clubedosbarbas.api.domain.Chat.repository.MensagemRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    private MensagemRepository repository;

    @PostMapping("/mensagens")
    @Transactional
    public ResponseEntity<Void> enviar(@RequestBody @Valid DadosEnvioMensagem dados) {
        var mensagem = new Mensagem(null, dados.remetenteId(), dados.remetenteTipo(), dados.destinatarioId(), dados.destinatarioTipo(), dados.conteudo(), LocalDateTime.now());
        repository.save(mensagem);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversas")
    public ResponseEntity<List<DadosListagemMensagem>> getConversa(
            @RequestParam Long usuario1Id,
            @RequestParam Long usuario2Id) {

        var mensagens = repository.findByRemetenteIdAndDestinatarioIdOrRemetenteIdAndDestinatarioIdOrderByDataEnvioAsc(
                usuario1Id, usuario2Id, usuario2Id, usuario1Id);
        
        var listaDto = mensagens.stream().map(DadosListagemMensagem::new).toList();
        return ResponseEntity.ok(listaDto);
    }
}
