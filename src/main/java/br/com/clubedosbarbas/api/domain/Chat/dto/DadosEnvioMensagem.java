package br.com.clubedosbarbas.api.domain.Chat.dto;

import br.com.clubedosbarbas.api.domain.Chat.TipoParticipante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEnvioMensagem(
        @NotNull Long remetenteId,
        @NotNull TipoParticipante remetenteTipo,
        @NotNull Long destinatarioId,
        @NotNull TipoParticipante destinatarioTipo,
        @NotBlank String conteudo
) {}
