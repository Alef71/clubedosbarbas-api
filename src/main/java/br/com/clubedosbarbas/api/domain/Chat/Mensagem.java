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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "chat_mensagens")
@Entity(name = "Mensagem")
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private StatusMensagem status;

    public Mensagem(Long id, Long remetenteId, TipoParticipante remetenteTipo, Long destinatarioId,
                    TipoParticipante destinatarioTipo, String conteudo, LocalDateTime dataEnvio, StatusMensagem status) {
        this.id = id;
        this.remetenteId = remetenteId;
        this.remetenteTipo = remetenteTipo;
        this.destinatarioId = destinatarioId;
        this.destinatarioTipo = destinatarioTipo;
        this.conteudo = conteudo;
        this.dataEnvio = dataEnvio;
        this.status = status;
    }

    // --- GETTERS E SETTERS MANUAIS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRemetenteId() { return remetenteId; }
    public void setRemetenteId(Long remetenteId) { this.remetenteId = remetenteId; }
    public TipoParticipante getRemetenteTipo() { return remetenteTipo; }
    public void setRemetenteTipo(TipoParticipante remetenteTipo) { this.remetenteTipo = remetenteTipo; }
    public Long getDestinatarioId() { return destinatarioId; }
    public void setDestinatarioId(Long destinatarioId) { this.destinatarioId = destinatarioId; }
    public TipoParticipante getDestinatarioTipo() { return destinatarioTipo; }
    public void setDestinatarioTipo(TipoParticipante destinatarioTipo) { this.destinatarioTipo = destinatarioTipo; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }
    public StatusMensagem getStatus() { return status; }
    public void setStatus(StatusMensagem status) { this.status = status; }
}
