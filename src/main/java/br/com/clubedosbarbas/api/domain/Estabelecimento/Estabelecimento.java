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
    
    private LocalTime horarioAbertura;
    
    private LocalTime horarioFim;

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
}