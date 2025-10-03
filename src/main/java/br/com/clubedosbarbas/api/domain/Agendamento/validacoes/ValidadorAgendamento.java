package br.com.clubedosbarbas.api.domain.Agendamento.validacoes;

import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;

public interface ValidadorAgendamento {
    void validar(DadosAgendamento dados);
}
