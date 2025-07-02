package com.pi2.monity_edu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TipoArquivoNaoSuportadoException extends RuntimeException {
    public TipoArquivoNaoSuportadoException(String message) {
        super(message);
    }
}