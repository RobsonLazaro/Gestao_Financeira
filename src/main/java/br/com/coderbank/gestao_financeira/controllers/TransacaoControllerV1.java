package br.com.coderbank.gestao_financeira.controllers;

import br.com.coderbank.gestao_financeira.dtos.requests.EntradaRequestDTO;
import br.com.coderbank.gestao_financeira.dtos.responses.TransacaoResponseDTO;
import br.com.coderbank.gestao_financeira.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/transacoes/entradas")
public class TransacaoControllerV1 {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> cadastrarTransacaoDeEntrada(@Valid @RequestBody EntradaRequestDTO entradaRequestDTO){
        return ResponseEntity.status(201).body(transacaoService.cadastrarTransacaoDeEntrada(entradaRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> listarTransacaoDeEntrada(@RequestParam (required = false) LocalDate dataInicio, @RequestParam(required = false) LocalDate dataFim){
        return ResponseEntity.status(200).body(transacaoService.listarTransacoesDeEntrada(dataInicio,dataFim));
    }


}
