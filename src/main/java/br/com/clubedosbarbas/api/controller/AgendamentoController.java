package br.com.clubedosbarbas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Agendamento.StatusAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosDetalhamentoAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.repository.AgendamentoRepository;
import br.com.clubedosbarbas.api.domain.Agendamento.service.AgendaDeAgendamentosService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendaDeAgendamentosService agendaService;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAgendamento> agendar(@RequestBody @Valid DadosAgendamento dados) {
        var agendamento = agendaService.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAgendamento(agendamento));
    }

    @PatchMapping("/{id}/confirmar")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAgendamento> confirmar(@PathVariable Long id) {
        var agendamento = agendamentoRepository.getReferenceById(id);
        agendamento.setStatus(StatusAgendamento.CONFIRMADO);
        return ResponseEntity.ok(new DadosDetalhamentoAgendamento(agendamento));
    }

    @PatchMapping("/{id}/cancelar")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAgendamento> cancelar(@PathVariable Long id) {
        var agendamento = agendamentoRepository.getReferenceById(id);
        agendamento.setStatus(StatusAgendamento.CANCELADO_PELO_BARBEIRO);
        return ResponseEntity.ok(new DadosDetalhamentoAgendamento(agendamento));
    }
}