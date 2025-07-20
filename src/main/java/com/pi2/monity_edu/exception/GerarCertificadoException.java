package com.pi2.monity_edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GerarCertificadoException extends RuntimeException {
    public GerarCertificadoException(String message) {
        super(message);
    }
}