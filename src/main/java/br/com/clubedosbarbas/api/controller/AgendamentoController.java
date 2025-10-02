package br.com.clubedosbarbas.api.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Agendamento.Agendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.StatusAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosDetalhamentoAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.repository.AgendamentoRepository;
import br.com.clubedosbarbas.api.domain.Barbeiro.repository.BarbeiroRepository;
import br.com.clubedosbarbas.api.domain.Cliente.repository.ClienteRepository;
import br.com.clubedosbarbas.api.domain.Servico.repository.ServicoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private BarbeiroRepository barbeiroRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAgendamento> agendar(@RequestBody @Valid DadosAgendamento dados) {
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var barbeiro = barbeiroRepository.getReferenceById(dados.idBarbeiro());
        var servicos = new HashSet<>(servicoRepository.findAllById(dados.idsServicos()));

        var valorTotal = servicos.stream().mapToDouble(s -> s.getValor()).sum();

        var agendamento = new Agendamento(
                null,
                barbeiro,
                cliente,
                servicos,
                dados.dataHora(),
                StatusAgendamento.PENDENTE_CONFIRMACAO,
                valorTotal
        );

        agendamentoRepository.save(agendamento);

        return ResponseEntity.ok(new DadosDetalhamentoAgendamento(agendamento));
    }
}