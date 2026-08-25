package br.com.coderbank.gestao_financeira.dtos.requests;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SaidaRequestDTO(
        @NotNull
        @DecimalMin("0.01")
        BigDecimal valor,

        @NotNull
        @PastOrPresent(message = "A data não pode ser futura.")
        LocalDate data,

        @NotBlank
        @Size(min = 3, max = 100)
        String descricao,

        @NotNull
        UUID idCategoria
) {
}
