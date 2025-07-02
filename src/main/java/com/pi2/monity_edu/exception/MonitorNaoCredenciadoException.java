package com.pi2.monity_edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class MonitorNaoCredenciadoException extends RuntimeException {
    public MonitorNaoCredenciadoException(String message) {
        super(message);
    }
}