package br.com.coderbank.gestao_financeira.dtos.responses;

import java.math.BigDecimal;

public record ResumoFinanceiroResponseDTO(
        PeriodoResponseDTO periodo,
        BigDecimal totalEntradas,
        BigDecimal totalSaidas,
        BigDecimal saldo
) {
}
