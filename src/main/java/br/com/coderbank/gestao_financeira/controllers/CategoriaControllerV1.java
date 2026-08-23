package br.com.coderbank.gestao_financeira.controllers;

import br.com.coderbank.gestao_financeira.dtos.requests.CategoriaRequestDTO;
import br.com.coderbank.gestao_financeira.dtos.responses.CategoriaResponseDTO;
import br.com.coderbank.gestao_financeira.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaControllerV1 {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> cadastrar(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.cadastrar(categoriaRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarTodasCategorias(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.listarCategorias());
    }

    @PatchMapping("/{idCategoria}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(@PathVariable UUID idCategoria, @RequestBody @Valid CategoriaRequestDTO categoriaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.atualizarCategoria(idCategoria, categoriaRequestDTO));
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID idCategoria){
        categoriaService.deletarCategoria(idCategoria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
