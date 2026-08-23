package br.com.coderbank.gestao_financeira.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(
        @NotBlank(message = "O campo nome é obrigatório.")
        @Size(min = 2, max = 50)
        String nome
) {
}
