package com.agibank.teste.exception;

import org.springframework.http.HttpStatus;

public class ClienteNaoEncontradoException extends HttpException {
    public ClienteNaoEncontradoException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
