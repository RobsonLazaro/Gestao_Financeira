package br.com.coderbank.gestao_financeira.controllers;

import br.com.coderbank.gestao_financeira.dtos.requests.EntradaRequestDTO;
import br.com.coderbank.gestao_financeira.dtos.requests.SaidaRequestDTO;
import br.com.coderbank.gestao_financeira.dtos.requests.TransacaoPatchDTO;
import br.com.coderbank.gestao_financeira.dtos.responses.TransacaoResponseDTO;
import br.com.coderbank.gestao_financeira.entities.enums.TipoTransacao;
import br.com.coderbank.gestao_financeira.services.TransacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/transacoes")
public class TransacaoControllerV1 {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping("/entradas")
    public ResponseEntity<TransacaoResponseDTO> cadastrarTransacaoDeEntrada(@Valid @RequestBody EntradaRequestDTO entradaRequestDTO){
        return ResponseEntity.status(201).body(transacaoService.cadastrarTransacaoDeEntrada(entradaRequestDTO));
    }

    @GetMapping("/entradas")
    public ResponseEntity<List<TransacaoResponseDTO>> listarTransacaoDeEntrada(@RequestParam (required = false) LocalDate dataInicio, @RequestParam(required = false) LocalDate dataFim){
        return ResponseEntity.status(200).body(transacaoService.listarTransacoesDeEntrada(dataInicio,dataFim));
    }

    @PostMapping("/saidas")
    public ResponseEntity<TransacaoResponseDTO> cadastrarTransacaoDeSaida(@Valid @RequestBody SaidaRequestDTO saidaRequestDTO){
        return ResponseEntity.status(201).body(transacaoService.cadastrarTransacaoDeSaida(saidaRequestDTO));
    }

    @GetMapping("/saidas")
    public ResponseEntity<List<TransacaoResponseDTO>> listarTransacaoDeSaida( @RequestParam(required = false) LocalDate dataInicio, @RequestParam(required = false) LocalDate dataFim, @RequestParam(required = false) UUID idCategoria){
        return ResponseEntity.status(200).body(transacaoService.listarTransacoesDeSaida( dataInicio, dataFim,idCategoria));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> listarTransacoes(@RequestParam (required = false) TipoTransacao tipo, @RequestParam (required = false) UUID idCategoria, @RequestParam (required = false) LocalDate dataInicio, @RequestParam (required = false) LocalDate dataFim){
        return ResponseEntity.status(200).body(transacaoService.listarTransacoes(tipo, idCategoria, dataInicio, dataFim));
    }

    @PatchMapping("/{idTransacao}")
    public ResponseEntity<TransacaoResponseDTO> atualizarTransacao(@PathVariable UUID idTransacao, @RequestBody TransacaoPatchDTO transacaoPatchDTO){
        return ResponseEntity.status(200).body(transacaoService.atualizarTransacao(idTransacao, transacaoPatchDTO));
    }

    @DeleteMapping("/{idTransacao}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable UUID idTransacao) {
        transacaoService.deletarTransacao(idTransacao);
        return ResponseEntity.noContent().build();
    }
}
