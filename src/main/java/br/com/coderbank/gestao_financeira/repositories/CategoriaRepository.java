package br.com.coderbank.gestao_financeira.repositories;

import br.com.coderbank.gestao_financeira.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}
