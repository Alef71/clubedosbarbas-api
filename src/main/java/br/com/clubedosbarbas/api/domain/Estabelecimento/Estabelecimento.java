package br.com.clubedosbarbas.api.domain.Estabelecimento;

import java.time.LocalTime;
import java.util.Objects;

import br.com.clubedosbarbas.api.domain.Estabelecimento.dto.DadosAtualizacaoEstabelecimento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "estabelecimento")
@Entity(name = "Estabelecimento")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalTime horarioAbertura;

    private LocalTime horarioFim;

    // Construtor vazio
    public Estabelecimento() {
    }

    // Construtor com todos os argumentos
    public Estabelecimento(Long id, String nome, LocalTime horarioAbertura, LocalTime horarioFim) {
        this.id = id;
        this.nome = nome;
        this.horarioAbertura = horarioAbertura;
        this.horarioFim = horarioFim;
    }

    public void atualizarInformacoes(DadosAtualizacaoEstabelecimento dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.horarioAbertura() != null) {
            this.horarioAbertura = dados.horarioAbertura();
        }
        if (dados.horarioFim() != null) {
            this.horarioFim = dados.horarioFim();
        }
    }

    // --- MÉTODOS GETTERS E SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    // --- MÉTODOS equals e hashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estabelecimento that = (Estabelecimento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
