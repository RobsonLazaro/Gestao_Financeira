package br.com.coderbank.gestao_financeira.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
