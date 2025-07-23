package com.pi2.monity_edu.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvaliacaoDTO {

    @NotNull(message = "A nota não pode ser nula.")
    @Min(value = 1, message = "A nota deve ser no mínimo 1.")
    @Max(value = 5, message = "A nota deve ser no máximo 5.")
    private int nota;
}