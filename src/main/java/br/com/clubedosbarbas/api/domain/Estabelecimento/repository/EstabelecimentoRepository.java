package br.com.clubedosbarbas.api.domain.Estabelecimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clubedosbarbas.api.domain.Estabelecimento.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
}
