package br.com.clubedosbarbas.api.domain.Barbeiro.dto;

import br.com.clubedosbarbas.api.domain.Barbeiro.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroBarbeiro(
        @NotBlank
        String nome,

        @NotBlank
        String username,

        @NotBlank
        String senha,

        String celular,

        @NotNull
        TipoUsuario tipo
) {
}
