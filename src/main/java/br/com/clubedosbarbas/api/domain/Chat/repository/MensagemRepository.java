package br.com.clubedosbarbas.api.domain.Chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clubedosbarbas.api.domain.Chat.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByRemetenteIdAndDestinatarioIdOrRemetenteIdAndDestinatarioIdOrderByDataEnvioAsc(
        Long usuario1Id, Long usuario2Id, Long usuario2Id_2, Long usuario1Id_2
    );
}
