package br.com.clubedosbarbas.api.domain.Agendamento.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;
import br.com.clubedosbarbas.api.domain.Estabelecimento.repository.EstabelecimentoRepository;
import br.com.clubedosbarbas.api.infra.exception.ValidacaoException;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
   
    @Override
    public void validar(DadosAgendamento dados) {
        
        var estabelecimento = estabelecimentoRepository.findById(1L).orElse(null);
        if (estabelecimento == null) {
            throw new ValidacaoException("Estabelecimento não configurado.");
        }

        var dataAgendamento = dados.dataHora();
        var horarioAgendamento = dataAgendamento.toLocalTime();

        if (horarioAgendamento.isBefore(estabelecimento.getHorario_abertura()) || horarioAgendamento.isAfter(estabelecimento.getHorario_fechamento())) {
            throw new ValidacaoException("Agendamento fora do horário de funcionamento da barbearia.");
        }
    }
}
