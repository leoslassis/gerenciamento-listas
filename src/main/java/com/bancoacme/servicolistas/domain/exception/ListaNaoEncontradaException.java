package com.bancoacme.servicolistas.domain.exception;

public class ListaNaoEncontradaException extends RuntimeException {
    public ListaNaoEncontradaException(String nome) {
        super("Lista com nome '" + nome + "' não encontrada.");
    }
}
