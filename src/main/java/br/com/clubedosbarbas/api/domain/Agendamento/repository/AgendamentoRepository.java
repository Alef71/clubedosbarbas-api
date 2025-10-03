package br.com.clubedosbarbas.api.domain.Agendamento.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clubedosbarbas.api.domain.Agendamento.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByBarbeiroIdAndDataHora(Long idBarbeiro, LocalDateTime dataHora);
}
