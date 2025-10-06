package br.com.clubedosbarbas.api.domain.Agendamento.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; 

import br.com.clubedosbarbas.api.domain.Agendamento.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>, JpaSpecificationExecutor<Agendamento> {

    boolean existsByBarbeiroIdAndDataHora(Long idBarbeiro, LocalDateTime dataHora);
    
}
