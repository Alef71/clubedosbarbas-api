package br.com.clubedosbarbas.api.domain.Barbeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails; // <-- IMPORT ADICIONADO

import br.com.clubedosbarbas.api.domain.Barbeiro.Barbeiro;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    // Método que o Spring Security usará para buscar um utilizador pelo username
    UserDetails findByUsername(String username);

    boolean existsByCelular(String celular);

}
