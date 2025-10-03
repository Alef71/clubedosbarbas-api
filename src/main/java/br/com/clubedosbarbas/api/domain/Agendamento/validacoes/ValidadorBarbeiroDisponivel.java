package br.com.clubedosbarbas.api.domain.Agendamento.validacoes;

import org.springframework.stereotype.Component;

import br.com.clubedosbarbas.api.domain.Agendamento.dto.DadosAgendamento;
import br.com.clubedosbarbas.api.domain.BloqueioHorario.repository.BloqueioHorarioRepository;
import br.com.clubedosbarbas.api.domain.HorarioTrabalho.repository.HorarioTrabalhoRepository;
import br.com.clubedosbarbas.api.infra.exception.ValidacaoException;

@Component
public class ValidadorBarbeiroDisponivel implements ValidadorAgendamento {

    private final HorarioTrabalhoRepository horarioTrabalhoRepository;
    private final BloqueioHorarioRepository bloqueioHorarioRepository;

    public ValidadorBarbeiroDisponivel(HorarioTrabalhoRepository horarioTrabalhoRepository, BloqueioHorarioRepository bloqueioHorarioRepository) {
        this.horarioTrabalhoRepository = horarioTrabalhoRepository;
        this.bloqueioHorarioRepository = bloqueioHorarioRepository;
    }

    @Override
    public void validar(DadosAgendamento dados) {
        var diaDaSemana = dados.dataHora().getDayOfWeek();
        var horario = dados.dataHora().toLocalTime();

        var trabalhaNoDia = horarioTrabalhoRepository.existsByBarbeiroIdAndDiaDaSemanaAndHorarioInicioLessThanEqualAndHorarioFimGreaterThanEqual(
            dados.idBarbeiro(), diaDaSemana, horario, horario);

        if (!trabalhaNoDia) {
            throw new ValidacaoException("O barbeiro selecionado não atende neste dia ou horário.");
        }

        var possuiBloqueio = bloqueioHorarioRepository.existsByBarbeiroIdAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(
            dados.idBarbeiro(), dados.dataHora(), dados.dataHora());

        if (possuiBloqueio) {
            throw new ValidacaoException("Horário indisponível devido a um bloqueio na agenda do barbeiro.");
        }
    }
}