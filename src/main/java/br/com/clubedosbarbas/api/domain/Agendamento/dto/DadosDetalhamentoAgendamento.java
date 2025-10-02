package br.com.clubedosbarbas.api.domain.Agendamento.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.clubedosbarbas.api.domain.Agendamento.Agendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.StatusAgendamento;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosListagemServico;

public record DadosDetalhamentoAgendamento(
        Long id,
        String nomeCliente,
        String nomeBarbeiro,
        List<DadosListagemServico> servicos,
        LocalDateTime dataHora,
        StatusAgendamento status,
        Double valorTotal
) {
    public DadosDetalhamentoAgendamento(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getCliente().getNome(),
                agendamento.getBarbeiro().getNome(),
                agendamento.getServicos().stream().map(DadosListagemServico::new).collect(Collectors.toList()),
                agendamento.getDataHora(),
                agendamento.getStatus(),
                agendamento.getValorTotal()
        );
    }
}
