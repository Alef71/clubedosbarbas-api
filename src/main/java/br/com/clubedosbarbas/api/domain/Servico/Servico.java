package br.com.clubedosbarbas.api.domain.Servico;

import br.com.clubedosbarbas.api.domain.Servico.dto.DadosAtualizacaoServico;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosCadastroServico;
import jakarta.persistence.*;
import java.util.Objects;

@Table(name = "servico")
@Entity(name = "Servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Integer duracaoEmMinutos; // Corrigido para camelCase
    private Double valor;

    // Construtor vazio
    public Servico() {
    }

    // Construtor com todos os argumentos
    public Servico(Long id, String nome, String descricao, Integer duracaoEmMinutos, Double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.duracaoEmMinutos = duracaoEmMinutos;
        this.valor = valor;
    }

    // Construtor a partir do DTO de cadastro
    public Servico(DadosCadastroServico dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.duracaoEmMinutos = dados.duracaoEmMinutos();
        this.valor = dados.valor();
    }

    public void atualizarInformacoes(DadosAtualizacaoServico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.duracaoEmMinutos() != null) {
            this.duracaoEmMinutos = dados.duracaoEmMinutos();
        }
        if (dados.valor() != null) {
            this.valor = dados.valor();
        }
    }

    // --- MÉTODOS GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getDuracaoEmMinutos() { return duracaoEmMinutos; }
    public void setDuracaoEmMinutos(Integer duracaoEmMinutos) { this.duracaoEmMinutos = duracaoEmMinutos; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    // --- MÉTODOS equals e hashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(id, servico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}