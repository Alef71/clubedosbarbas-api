package br.com.clubedosbarbas.api.domain.Chat.dto;

import java.time.LocalDateTime;

import br.com.clubedosbarbas.api.domain.Chat.Mensagem;
import br.com.clubedosbarbas.api.domain.Chat.TipoParticipante;

public record DadosListagemMensagem(
        Long id,
        Long remetenteId,
        TipoParticipante remetenteTipo,
        String conteudo,
        LocalDateTime dataEnvio
) {
    public DadosListagemMensagem(Mensagem mensagem) {
        this(mensagem.getId(), mensagem.getRemetenteId(), mensagem.getRemetenteTipo(), mensagem.getConteudo(), mensagem.getDataEnvio());
    }
}
