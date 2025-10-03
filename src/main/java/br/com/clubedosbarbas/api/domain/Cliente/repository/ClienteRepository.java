package br.com.clubedosbarbas.api.domain.Cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clubedosbarbas.api.domain.Cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByTelefone(String telefone);
}

