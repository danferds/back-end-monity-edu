package com.pi2.monity_edu.dto;

import java.util.UUID;

import com.pi2.monity_edu.model.StatusMonitor;

import lombok.Value;

@Value
public class CredenciamentoResponseDTO {
    UUID id;
    String instituicaoEnsino;
    String curso;
    String periodoAtual;
    String emailInstitucional;
    StatusMonitor status;
}