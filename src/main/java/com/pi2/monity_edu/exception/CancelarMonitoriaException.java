package com.pi2.monity_edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CancelarMonitoriaException extends RuntimeException {
    public CancelarMonitoriaException(String message) {
        super(message);
    }
}