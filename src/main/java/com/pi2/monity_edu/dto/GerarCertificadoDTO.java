package com.pi2.monity_edu.dto;

import lombok.Data;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

@Data
public class GerarCertificadoDTO {

    @NotNull(message = "O ID da monitoria não pode ser nulo.")
    private UUID monitoriaId;
}