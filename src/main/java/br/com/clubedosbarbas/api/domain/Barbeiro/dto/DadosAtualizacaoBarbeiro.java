package br.com.clubedosbarbas.api.domain.Barbeiro.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoBarbeiro(
    @NotNull 
    Long id,
    String nome,
    String celular
) {
    
}
