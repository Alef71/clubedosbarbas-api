package br.com.clubedosbarbas.api.domain.HorarioTrabalho.repository;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clubedosbarbas.api.domain.HorarioTrabalho.HorarioTrabalho;

public interface HorarioTrabalhoRepository extends JpaRepository<HorarioTrabalho, Long> {
    boolean existsByBarbeiroIdAndDiaDaSemanaAndHorarioInicioLessThanEqualAndHorarioFimGreaterThanEqual(Long idBarbeiro, DayOfWeek diaDaSemana, LocalTime horario, LocalTime horarioFim);
}
