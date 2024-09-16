package br.com.fiap.parking_meter.parking_meter.controller.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}

