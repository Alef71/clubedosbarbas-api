package br.com.clubedosbarbas.api.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clubedosbarbas.api.domain.Barbeiro.repository.BarbeiroRepository;
import br.com.clubedosbarbas.api.domain.Financeiro.MovimentacaoFinanceira;
import br.com.clubedosbarbas.api.domain.Financeiro.TipoMovimentacao;
import br.com.clubedosbarbas.api.domain.Financeiro.dto.DadosCadastroMovimentacao;
import br.com.clubedosbarbas.api.domain.Financeiro.dto.DadosListagemMovimentacao;
import br.com.clubedosbarbas.api.domain.Financeiro.dto.DadosRelatorioDiario;
import br.com.clubedosbarbas.api.domain.Financeiro.dto.DadosRelatorioMensal;
import br.com.clubedosbarbas.api.domain.Financeiro.repository.MovimentacaoFinanceiraRepository;
import br.com.clubedosbarbas.api.infra.dto.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("financeiro")
public class FinanceiroController {

    @Autowired
    private MovimentacaoFinanceiraRepository repository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @PostMapping("/movimentacoes")
    @Transactional
    public ResponseEntity<ApiResponse<DadosListagemMovimentacao>> cadastrar(@RequestBody @Valid DadosCadastroMovimentacao dados) {
        var barbeiro = barbeiroRepository.getReferenceById(dados.idBarbeiro());
        var movimentacao = new MovimentacaoFinanceira(null, dados.data(), dados.tipo(), dados.descricao(), dados.valor(), barbeiro);
        repository.save(movimentacao);
        return ResponseEntity.ok(ApiResponse.success(new DadosListagemMovimentacao(movimentacao)));
    }

    @GetMapping("/movimentacoes")
    public ResponseEntity<ApiResponse<List<DadosListagemMovimentacao>>> listarPorMes(
            @RequestParam Long idBarbeiro,
            @RequestParam int ano,
            @RequestParam int mes) {
        var dataInicio = LocalDate.of(ano, mes, 1);
        var dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());
        var movimentacoes = repository.findAllByBarbeiroIdAndDataBetween(idBarbeiro, dataInicio, dataFim);
        var listaDto = movimentacoes.stream().map(DadosListagemMovimentacao::new).toList();
        return ResponseEntity.ok(ApiResponse.success(listaDto));
    }

    @DeleteMapping("/movimentacoes/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/relatorio/mensal")
    public ResponseEntity<ApiResponse<DadosRelatorioMensal>> gerarRelatorioMensal(
            @RequestParam Long idBarbeiro,
            @RequestParam int ano,
            @RequestParam int mes) {

        var dataInicio = LocalDate.of(ano, mes, 1);
        var dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());
        var movimentacoes = repository.findAllByBarbeiroIdAndDataBetween(idBarbeiro, dataInicio, dataFim);

        var totalEntradas = movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.ENTRADA)
                .map(MovimentacaoFinanceira::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalDespesas = movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.DESPESA)
                .map(MovimentacaoFinanceira::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var lucro = totalEntradas.subtract(totalDespesas);

        var relatorio = new DadosRelatorioMensal(ano, mes, totalEntradas, totalDespesas, lucro);
        return ResponseEntity.ok(ApiResponse.success(relatorio));
    }

    @GetMapping("/relatorio/diario")
    public ResponseEntity<ApiResponse<DadosRelatorioDiario>> gerarRelatorioDiario(
            @RequestParam Long idBarbeiro,
            @RequestParam LocalDate data) {

        var movimentacoes = repository.findAllByBarbeiroIdAndData(idBarbeiro, data);

        var totalEntradas = movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.ENTRADA)
                .map(MovimentacaoFinanceira::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalDespesas = movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.DESPESA)
                .map(MovimentacaoFinanceira::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var lucro = totalEntradas.subtract(totalDespesas);

        var relatorio = new DadosRelatorioDiario(data, totalEntradas, totalDespesas, lucro);
        return ResponseEntity.ok(ApiResponse.success(relatorio));
    }
}
