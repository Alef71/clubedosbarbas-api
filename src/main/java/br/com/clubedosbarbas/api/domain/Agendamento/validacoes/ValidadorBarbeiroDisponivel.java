package br.com.clubedosbarbas.api.domain.Agendamento.validacoes;

import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;
import br.com.clubedosbarbas.api.domain.BloqueioHorario.repository.BloqueioHorarioRepository;
import br.com.clubedosbarbas.api.domain.HorarioTrabalho.repository.HorarioTrabalhoRepository;
import br.com.clubedosbarbas.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorBarbeiroDisponivel implements ValidadorAgendamento {

    @Autowired
    private HorarioTrabalhoRepository horarioTrabalhoRepository;
    @Autowired
    private BloqueioHorarioRepository bloqueioHorarioRepository;

    @Override
    public void validar(DadosAgendamento dados) {
        var diaDaSemana = dados.dataHora().getDayOfWeek();
        var horario = dados.dataHora().toLocalTime();

        // 1. Verifica se o barbeiro trabalha no dia da semana solicitado
        var trabalhaNoDia = horarioTrabalhoRepository.existsByBarbeiroIdAndDiaDaSemanaAndHorarioInicioLessThanEqualAndHorarioFimGreaterThanEqual(
            dados.idBarbeiro(), diaDaSemana, horario, horario);

        if (!trabalhaNoDia) {
            throw new ValidacaoException("O barbeiro selecionado não atende neste dia ou horário.");
        }

        // 2. Verifica se existe um bloqueio no horário solicitado
        var possuiBloqueio = bloqueioHorarioRepository.existsByBarbeiroIdAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(
            dados.idBarbeiro(), dados.dataHora(), dados.dataHora());

        if (possuiBloqueio) {
            throw new ValidacaoException("Horário indisponível devido a um bloqueio na agenda do barbeiro.");
        }
    }
}
