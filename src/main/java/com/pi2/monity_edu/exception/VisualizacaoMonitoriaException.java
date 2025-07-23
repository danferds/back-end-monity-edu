package com.pi2.monity_edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class VisualizacaoMonitoriaException extends RuntimeException {
    public VisualizacaoMonitoriaException(String message) {
        super(message);
    }
}