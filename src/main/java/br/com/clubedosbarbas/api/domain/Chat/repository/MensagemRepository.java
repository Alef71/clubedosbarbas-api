package br.com.clubedosbarbas.api.domain.Chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; 

import br.com.clubedosbarbas.api.domain.Chat.Mensagem; 

public interface MensagemRepository extends JpaRepository<Mensagem, Long>, JpaSpecificationExecutor<Mensagem> {
}