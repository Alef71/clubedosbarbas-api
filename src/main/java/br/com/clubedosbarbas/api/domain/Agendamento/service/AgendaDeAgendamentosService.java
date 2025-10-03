package br.com.clubedosbarbas.api.domain.Agendamento.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.clubedosbarbas.api.domain.Agendamento.Agendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.StatusAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;
import br.com.clubedosbarbas.api.domain.Agendamento.repository.AgendamentoRepository;
import br.com.clubedosbarbas.api.domain.Agendamento.validacoes.ValidadorAgendamento;
import br.com.clubedosbarbas.api.domain.Barbeiro.repository.BarbeiroRepository;
import br.com.clubedosbarbas.api.domain.Cliente.repository.ClienteRepository;
import br.com.clubedosbarbas.api.domain.Servico.repository.ServicoRepository;
import br.com.clubedosbarbas.api.infra.exception.ValidacaoException;

@Service
public class AgendaDeAgendamentosService {

    private final AgendamentoRepository agendamentoRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;
    private final List<ValidadorAgendamento> validadores;

    // @Autowired foi REMOVIDO daqui
    public AgendaDeAgendamentosService(AgendamentoRepository agendamentoRepository, BarbeiroRepository barbeiroRepository, ClienteRepository clienteRepository, ServicoRepository servicoRepository, List<ValidadorAgendamento> validadores) {
        this.agendamentoRepository = agendamentoRepository;
        this.barbeiroRepository = barbeiroRepository;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
        this.validadores = validadores;
    }

    public Agendamento agendar(DadosAgendamento dados) {
        if (!clienteRepository.existsById(dados.idCliente())) {
            throw new ValidacaoException("Id do cliente informado não existe!");
        }
        if (!barbeiroRepository.existsById(dados.idBarbeiro())) {
            throw new ValidacaoException("Id do barbeiro informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var barbeiroPossuiOutroAgendamentoNoMesmoHorario = agendamentoRepository.existsByBarbeiroIdAndDataHora(dados.idBarbeiro(), dados.dataHora());
        if (barbeiroPossuiOutroAgendamentoNoMesmoHorario) {
            throw new ValidacaoException("Barbeiro já possui outro agendamento nesse mesmo horário");
        }

        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var barbeiro = barbeiroRepository.getReferenceById(dados.idBarbeiro());
        var servicos = new HashSet<>(servicoRepository.findAllById(dados.idsServicos()));
        var valorTotal = servicos.stream().mapToDouble(s -> s.getValor()).sum();

        var agendamento = new Agendamento(null, barbeiro, cliente, servicos, dados.dataHora(), StatusAgendamento.PENDENTE_CONFIRMACAO, valorTotal);
        agendamentoRepository.save(agendamento);
        return agendamento;
    }
}
