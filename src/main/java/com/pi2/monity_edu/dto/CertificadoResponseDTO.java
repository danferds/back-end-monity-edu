package com.pi2.monity_edu.dto;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class CertificadoResponseDTO {
    UUID id;
    String nomeArquivo;
    String tituloMonitoria;
    LocalDateTime dataCriacao;
}