package br.com.clubedosbarbas.api.domain.Financeiro;

import br.com.clubedosbarbas.api.domain.Barbeiro.Barbeiro;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "financeiro_movimentacoes")
@Entity(name = "MovimentacaoFinanceira")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
}
