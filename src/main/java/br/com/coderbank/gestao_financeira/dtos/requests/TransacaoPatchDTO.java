package br.com.coderbank.gestao_financeira.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoPatchDTO (
        BigDecimal valor,
        LocalDate data,
        String descricao,
        UUID idCategoria
){
}
