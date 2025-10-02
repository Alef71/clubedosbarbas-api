package br.com.clubedosbarbas.api.domain.Barbeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clubedosbarbas.api.domain.Barbeiro.Barbeiro;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

}