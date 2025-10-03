package br.com.clubedosbarbas.api.domain.BloqueioHorario.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clubedosbarbas.api.domain.BloqueioHorario.BloqueioHorario;

public interface BloqueioHorarioRepository extends JpaRepository<BloqueioHorario, Long> {
    boolean existsByBarbeiroIdAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(Long idBarbeiro, LocalDateTime dataHora, LocalDateTime dataHoraFim);
}