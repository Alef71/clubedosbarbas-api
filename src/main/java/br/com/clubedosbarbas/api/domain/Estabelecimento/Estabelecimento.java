package br.com.clubedosbarbas.api.domain.Estabelecimento;

import br.com.clubedosbarbas.api.domain.Estabelecimento.dto.DadosAtualizacaoEstabelecimento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Table(name = "estabelecimento")
@Entity(name = "Estabelecimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalTime horario_abertura;
    private LocalTime horario_fechamento;

    public void atualizarInformacoes(DadosAtualizacaoEstabelecimento dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.horario_abertura() != null) {
            this.horario_abertura = dados.horario_abertura();
        }
        if (dados.horario_fechamento() != null) {
            this.horario_fechamento = dados.horario_fechamento();
        }
    }
}
