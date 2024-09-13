package br.com.fiap.parking_meter.parking_meter.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Método para tratar ControllerNotFoundException
    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<Object> handleControllerNotFoundException(ControllerNotFoundException ex, WebRequest request) {
        // Cria uma resposta personalizada
        String errorMessage = ex.getMessage();

        // Retorna um ResponseEntity com o status HTTP 404 e a mensagem de erro
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    // Método para outras exceções genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        // Retorna um status 500 para erros inesperados
        return new ResponseEntity<>("Ocorreu um erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
