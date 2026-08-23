package br.com.coderbank.gestao_financeira.dtos.responses;

import java.util.UUID;

public record CategoriaResponseDTO(
        UUID idCategoria,
        String nome
) {
}
