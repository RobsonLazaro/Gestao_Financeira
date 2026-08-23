package br.com.coderbank.gestao_financeira.dtos.requests;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EntradaRequestDTO(
        @DecimalMin("0.01")
        @NotNull
        BigDecimal valor,

        @NotNull
        @PastOrPresent(message = "a data não pode ser futura.")
        LocalDate data,

        @Size(min = 3, max = 100)
        @NotBlank
        String descricao

) {
}
