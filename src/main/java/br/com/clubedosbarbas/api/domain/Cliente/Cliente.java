package br.com.clubedosbarbas.api.domain.Cliente;

import java.util.Objects;

import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosAtualizacaoCliente;
import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosCadastroCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "cliente")
@Entity(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String telefone;

    private String fotoUrl;

    // Construtor vazio
    public Cliente() {
    }

    // Construtor com todos os argumentos
    public Cliente(Long id, String nome, String telefone, String fotoUrl) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.fotoUrl = fotoUrl;
    }

    // Construtor para cadastro
    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.fotoUrl = dados.fotoUrl();
    }

    public void atualizarInformacoes(DadosAtualizacaoCliente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.fotoUrl() != null) {
            this.fotoUrl = dados.fotoUrl();
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    // --- MÉTODOS equals e hashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
