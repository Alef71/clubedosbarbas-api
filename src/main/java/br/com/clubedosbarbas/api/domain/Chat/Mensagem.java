package br.com.clubedosbarbas.api.domain.Chat;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "chat_mensagens")
@Entity(name = "Mensagem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long remetenteId;

    @Enumerated(EnumType.STRING)
    private TipoParticipante remetenteTipo;

    private Long destinatarioId;

    @Enumerated(EnumType.STRING)
    private TipoParticipante destinatarioTipo;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private LocalDateTime dataEnvio;
}