package com.gabrielrocha.exception;

public class RemocaoNaoAutorizada extends RuntimeException {
    public RemocaoNaoAutorizada(String message) {
        super(message);
    }
}
