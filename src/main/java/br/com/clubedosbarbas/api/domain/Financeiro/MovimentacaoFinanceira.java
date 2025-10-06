package br.com.clubedosbarbas.api.domain.Financeiro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import br.com.clubedosbarbas.api.domain.Barbeiro.Barbeiro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "financeiro_movimentacoes")
@Entity(name = "MovimentacaoFinanceira")
public class MovimentacaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;

    // Construtor vazio
    public MovimentacaoFinanceira() {
    }

    // Construtor com todos os argumentos
    public MovimentacaoFinanceira(Long id, LocalDate data, TipoMovimentacao tipo, String descricao, BigDecimal valor, Barbeiro barbeiro) {
        this.id = id;
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.barbeiro = barbeiro;
    }

    // --- MÉTODOS GETTERS E SETTERS ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    // --- MÉTODOS equals e hashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentacaoFinanceira that = (MovimentacaoFinanceira) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
