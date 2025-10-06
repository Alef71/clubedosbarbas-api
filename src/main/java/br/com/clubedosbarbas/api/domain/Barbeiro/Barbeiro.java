package br.com.clubedosbarbas.api.domain.Barbeiro;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.clubedosbarbas.api.domain.Barbeiro.dto.DadosAtualizacaoBarbeiro;
import br.com.clubedosbarbas.api.domain.Barbeiro.dto.DadosCadastroBarbeiro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "barbeiro")
@Entity(name = "Barbeiro")
public class Barbeiro implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Column(unique = true)
    private String username;
    private String senha;
    private String fotoUrl;
    @Column(unique = true)
    private String celular;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    // Construtor vazio
    public Barbeiro() {
    }

    // Construtor com todos os argumentos
    public Barbeiro(Long id, String nome, String username, String senha, String fotoUrl, String celular, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.senha = senha;
        this.fotoUrl = fotoUrl;
        this.celular = celular;
        this.tipo = tipo;
    }

    // Construtor para cadastro
    public Barbeiro(DadosCadastroBarbeiro dados) {
        this.nome = dados.nome();
        this.username = dados.username();
        this.senha = dados.senha();
        this.celular = dados.celular();
        this.tipo = dados.tipo();
    }

    public void atualizarInformacoes(DadosAtualizacaoBarbeiro dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.celular() != null) {
            this.celular = dados.celular();
        }
    }

    // --- MÉTODOS GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public void setUsername(String username) { this.username = username; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }


    // --- MÉTODOS DA INTERFACE UserDetails ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    // --- MÉTODOS equals e hashCode ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barbeiro barbeiro = (Barbeiro) o;
        return Objects.equals(id, barbeiro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

