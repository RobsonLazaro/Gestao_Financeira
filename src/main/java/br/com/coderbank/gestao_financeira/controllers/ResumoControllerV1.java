package br.com.coderbank.gestao_financeira.controllers;

import br.com.coderbank.gestao_financeira.dtos.responses.ResumoFinanceiroResponseDTO;
import br.com.coderbank.gestao_financeira.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/resumo")
public class ResumoControllerV1 {

    @Autowired
    TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<ResumoFinanceiroResponseDTO> gerarResumo(@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim){
        return ResponseEntity.status(200).body(transacaoService.gerarResumo(dataInicio,dataFim));
    }
}
