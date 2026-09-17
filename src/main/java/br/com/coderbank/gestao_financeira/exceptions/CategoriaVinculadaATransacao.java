package br.com.coderbank.gestao_financeira.exceptions;

public class CategoriaVinculadaATransacao extends RuntimeException {
    public CategoriaVinculadaATransacao() {

      super("Existe uma transação vinculada a essa Categoria, ela não pode ser excluida");
    }
}
