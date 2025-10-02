package br.com.clubedosbarbas.api.domain.Agendamento;

public enum StatusAgendamento {
    PENDENTE_CONFIRMACAO,
    CONFIRMADO,
    CANCELADO_PELO_CLIENTE,
    CANCELADO_PELO_BARBEIRO,
    CONCLUIDO
}