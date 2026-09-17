package br.com.coderbank.gestao_financeira.dtos.responses;

import java.time.LocalDate;

public record PeriodoResponseDTO (
        LocalDate dataInicio,
        LocalDate dataFim
){
}
