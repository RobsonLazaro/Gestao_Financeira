package br.com.coderbank.gestao_financeira.dtos.responses;

import br.com.coderbank.gestao_financeira.entities.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoResponseDTO(

        UUID idTransacao,
        TipoTransacao tipo,
        BigDecimal valor,
        LocalDate data,
        String descricao,
        CategoriaResponseDTO categoria,
        LocalDateTime dataCriacao
) {
}
