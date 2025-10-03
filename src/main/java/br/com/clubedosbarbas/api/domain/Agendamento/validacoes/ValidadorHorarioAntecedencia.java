package br.com.clubedosbarbas.api.domain.Agendamento.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;
import br.com.clubedosbarbas.api.infra.exception.ValidacaoException;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento {
    
    @Override
    public void validar(DadosAgendamento dados) {
        var dataAgendamento = dados.dataHora();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataAgendamento).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Agendamento deve ser realizado com antecedência mínima de 30 minutos");
        }
    }
}
