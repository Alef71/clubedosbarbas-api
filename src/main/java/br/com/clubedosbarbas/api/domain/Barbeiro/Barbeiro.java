package br.com.clubedosbarbas.api.domain.Barbeiro;

import br.com.clubedosbarbas.api.domain.Barbeiro.dto.DadosAtualizacaoBarbeiro;
import br.com.clubedosbarbas.api.domain.Barbeiro.dto.DadosCadastroBarbeiro;
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

@Table(name = "barbeiro")
@Entity(name = "Barbeiro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String username;
    private String senha;
    private String foto_url;
    private String celular;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

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


}

