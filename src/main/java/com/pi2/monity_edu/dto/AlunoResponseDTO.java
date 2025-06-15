package com.pi2.monity_edu.dto;

import com.pi2.monity_edu.model.SerieEscolar;
import lombok.Value;
import java.util.UUID;

@Value
public class AlunoResponseDTO {
    UUID id;
    String nome;
    String email;
    SerieEscolar serieEscolar;
}