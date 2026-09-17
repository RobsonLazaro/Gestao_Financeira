package br.com.coderbank.gestao_financeira.exceptions;

public class CategoriaRepetidaException extends RuntimeException {
    public CategoriaRepetidaException() {
        super("Essa categoria já foi criada.");
    }
}
