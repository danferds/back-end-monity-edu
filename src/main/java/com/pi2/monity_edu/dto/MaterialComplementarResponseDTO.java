package com.pi2.monity_edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialComplementarResponseDTO {
    UUID id;
    String nomeArquivo;
}