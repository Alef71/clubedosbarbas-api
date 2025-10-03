package br.com.clubedosbarbas.api.domain.Financeiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clubedosbarbas.api.domain.Financeiro.MovimentacaoFinanceira;

public interface MovimentacaoFinanceiraRepository extends JpaRepository<MovimentacaoFinanceira, Long> {
    List<MovimentacaoFinanceira> findAllByBarbeiroIdAndDataBetween(Long idBarbeiro, LocalDate dataInicio, LocalDate dataFim);
    List<MovimentacaoFinanceira> findAllByBarbeiroIdAndData(Long idBarbeiro, LocalDate data);
}