package com.agibank.teste.exception;

import org.springframework.http.HttpStatus;

public class ClienteJaCadastradoException extends HttpException {
    public ClienteJaCadastradoException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
