package br.com.clubedosbarbas.api.domain.Cliente;

import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosAtualizacaoCliente;
import br.com.clubedosbarbas.api.domain.Cliente.dto.DadosCadastroCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cliente")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true) 
    private String telefone;

    private String fotoUrl;

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
}
