package com.agibank.teste.handler;

import com.agibank.teste.dto.ErroDTO;
import com.agibank.teste.exception.HttpException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Responsavel por capturar exceções personalizadas do tipo HttpException e devolver mensagem e status http de acordo com a exceção
    @ResponseBody
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErroDTO> handlerHttpException(HttpException ex) {
        return ResponseEntity.status(ex.getHttpStatus().value()).body(new ErroDTO(ex.getMessage()));
    }

    // Erro De Conversão de objeto
    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroDTO> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String nomeParametro = ex.getName();
        String valorIncorreto = ex.getValue().toString();
        String tipoEsperado = ex.getRequiredType().getSimpleName();

        String mensagem = String.format("O parâmetro '%s' recebeu o valor '%s', que é inválido. O tipo esperado é '%s'.",
                nomeParametro, valorIncorreto, tipoEsperado);

        return new ResponseEntity<>(new ErroDTO(mensagem), HttpStatus.BAD_REQUEST);
    }

    // Erro de validação
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        String mensagem = "Erro de validação nos seguintes campos: " + errors;
        return new ResponseEntity<>(new ErroDTO(mensagem), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Erro de data
    @ResponseBody
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErroDTO> handleDateTimeParseException(DateTimeParseException ex) {
        String mensagem = "Erro de validação no dataNascimento: o formato desejado é: 2025-12-31.";
        return new ResponseEntity<>(new ErroDTO(mensagem), HttpStatus.BAD_REQUEST);
    }

    // Erro de cliente duplicado
    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String mensagem = "CPF já cadastrado. Caso você seja o responsável por este cadastro, por favor, entre em contato conosco enviando uma mensagem para 4002-8922 para mais informações.";
        return new ResponseEntity<>(new ErroDTO(mensagem), HttpStatus.BAD_REQUEST);
    }
}
