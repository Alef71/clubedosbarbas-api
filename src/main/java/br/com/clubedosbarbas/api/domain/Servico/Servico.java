package br.com.clubedosbarbas.api.domain.Servico;

import br.com.clubedosbarbas.api.domain.Servico.dto.DadosAtualizacaoServico;
import br.com.clubedosbarbas.api.domain.Servico.dto.DadosCadastroServico;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "servico")
@Entity(name = "Servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Integer duracao_em_minutos;
    private Double valor;

    public Servico(DadosCadastroServico dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.duracao_em_minutos = dados.duracao_em_minutos();
        this.valor = dados.valor();
    }

    public void atualizarInformacoes(DadosAtualizacaoServico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.duracao_em_minutos() != null) {
            this.duracao_em_minutos = dados.duracao_em_minutos();
        }
        if (dados.valor() != null) {
            this.valor = dados.valor();
        }
    }
}