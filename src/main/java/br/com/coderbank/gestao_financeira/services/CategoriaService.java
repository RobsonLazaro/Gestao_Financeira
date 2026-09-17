package br.com.coderbank.gestao_financeira.services;

import br.com.coderbank.gestao_financeira.dtos.requests.CategoriaRequestDTO;
import br.com.coderbank.gestao_financeira.dtos.responses.CategoriaResponseDTO;
import br.com.coderbank.gestao_financeira.entities.Categoria;
import br.com.coderbank.gestao_financeira.exceptions.RecursoNaoEncontradoException;
import br.com.coderbank.gestao_financeira.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public CategoriaResponseDTO cadastrar(CategoriaRequestDTO categoriaRequestDTO){

       Categoria categoriaNova = new Categoria();

       categoriaNova.setNome(categoriaRequestDTO.nome());

       var categoriaSalva = categoriaRepository.save(categoriaNova);

       return new CategoriaResponseDTO(categoriaSalva.getId(),categoriaSalva.getNome());

    }


    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoria -> new CategoriaResponseDTO(
                        categoria.getId(),
                        categoria.getNome()
                ))
                .toList();
    }

    public CategoriaResponseDTO atualizarCategoria(UUID uuid, CategoriaRequestDTO categoriaRequestDTO){

        Categoria categoria = categoriaRepository.findById(uuid).orElseThrow(( )-> new RecursoNaoEncontradoException());

        categoria.setNome(categoriaRequestDTO.nome());

        var categoriaAtualizada = categoriaRepository.save(categoria);

        return new CategoriaResponseDTO(categoriaAtualizada.getId(), categoriaAtualizada.getNome());

    }

    public void  deletarCategoria(UUID uuid) {
        Categoria categoria = categoriaRepository.findById(uuid).orElseThrow(() -> new RecursoNaoEncontradoException());

        categoriaRepository.delete(categoria);
    }

}


