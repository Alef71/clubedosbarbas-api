package br.com.clubedosbarbas.api.domain.Financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.clubedosbarbas.api.domain.Financeiro.MovimentacaoFinanceira;
import br.com.clubedosbarbas.api.domain.Financeiro.TipoMovimentacao;

public record DadosListagemMovimentacao(
        Long id,
        LocalDate data,
        TipoMovimentacao tipo,
        String descricao,
        BigDecimal valor
) {
    public DadosListagemMovimentacao(MovimentacaoFinanceira movimentacao) {
        this(movimentacao.getId(), movimentacao.getData(), movimentacao.getTipo(), movimentacao.getDescricao(), movimentacao.getValor());
    }
}