package br.com.clubedosbarbas.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Agendamento.Agendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.StatusAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosDetalhamentoAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.repository.AgendamentoRepository;
import br.com.clubedosbarbas.api.domain.Agendamento.repository.AgendamentoSpecifications;
import br.com.clubedosbarbas.api.domain.Agendamento.service.AgendaDeAgendamentosService;
import br.com.clubedosbarbas.api.infra.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<DadosDetalhamentoAgendamento>> agendar(@RequestBody @Valid DadosAgendamento dados) {
        var agendamento = agendaService.agendar(dados);
        return ResponseEntity.ok(ApiResponse.success(new DadosDetalhamentoAgendamento(agendamento)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DadosDetalhamentoAgendamento>>> buscar(
            @RequestParam(required = false) Long barbeiroId,
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim
    ) {
        Specification<Agendamento> spec = AgendamentoSpecifications.comFiltros(barbeiroId, clienteId, dataInicio, dataFim);
        var agendamentos = agendamentoRepository.findAll(spec);
        var listaDto = agendamentos.stream().map(DadosDetalhamentoAgendamento::new).toList();
        return ResponseEntity.ok(ApiResponse.success(listaDto));
    }

    @PatchMapping("/{id}/confirmar")
    @Transactional
    public ResponseEntity<ApiResponse<DadosDetalhamentoAgendamento>> confirmar(@PathVariable Long id) {
        var agendamento = agendamentoRepository.getReferenceById(id);
        agendamento.setStatus(StatusAgendamento.CONFIRMADO);
        return ResponseEntity.ok(ApiResponse.success(new DadosDetalhamentoAgendamento(agendamento)));
    }

    @PatchMapping("/{id}/cancelar")
    @Transactional
    public ResponseEntity<ApiResponse<DadosDetalhamentoAgendamento>> cancelar(@PathVariable Long id) {
        var agendamento = agendamentoRepository.getReferenceById(id);
        agendamento.setStatus(StatusAgendamento.CANCELADO_PELO_BARBEIRO);
        return ResponseEntity.ok(ApiResponse.success(new DadosDetalhamentoAgendamento(agendamento)));
    }
}

