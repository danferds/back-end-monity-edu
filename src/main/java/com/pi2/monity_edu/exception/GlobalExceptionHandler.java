package com.pi2.monity_edu.exception;

import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseFactory.error(HttpStatus.UNPROCESSABLE_ENTITY, "Dados de entrada inválidos.", errors);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmailJaCadastrado(EmailJaCadastradoException ex) {
        return ResponseFactory.error(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EmailInexistenteException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmailInexistente(EmailInexistenteException ex) {
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DominioInstitucionalInvalidoException.class)
    public ResponseEntity<ApiResponse<Object>> handleDominioInvalido(DominioInstitucionalInvalidoException ex) {
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseFactory.error(HttpStatus.NOT_FOUND, "Recurso não encontrado.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        return ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado no servidor.");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String genericMessage = "O corpo da requisição contém um valor em formato inválido.";

        if (ex.getCause() instanceof InvalidFormatException ifx) {
            String fieldName = ifx.getPath().get(ifx.getPath().size() - 1).getFieldName();
            String detailedMessage = String.format("O valor '%s' não é válido para o campo '%s'.", ifx.getValue(), fieldName);
            return ResponseFactory.error(HttpStatus.BAD_REQUEST, detailedMessage);
        }
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, genericMessage);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentials(BadCredentialsException ex) {
        return ResponseFactory.error(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseFactory.error(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(MonitorNaoCredenciadoException.class)
    public ResponseEntity<ApiResponse<Object>> handleMonitorNaoCredenciado(MonitorNaoCredenciadoException ex) {
        return ResponseFactory.error(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(HorarioInvalidoException.class)
    public ResponseEntity<ApiResponse<Object>> handleHorarioInvalido(HorarioInvalidoException ex) {
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(TipoArquivoNaoSuportadoException.class)
    public ResponseEntity<ApiResponse<Object>> handleTipoArquivoNaoSuportado(TipoArquivoNaoSuportadoException ex) {
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseFactory.error(HttpStatus.BAD_REQUEST, "Argumento inválido.");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<Object>> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex) {
        return ResponseFactory.error(HttpStatus.PAYLOAD_TOO_LARGE, "O arquivo excede o tamanho máximo permitido de 10MB.");
    }
}