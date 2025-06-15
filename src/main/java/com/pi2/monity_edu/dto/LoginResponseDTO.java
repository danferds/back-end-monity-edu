package com.pi2.monity_edu.dto;

import com.pi2.monity_edu.model.SerieEscolar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private String token;
    private UUID id;
    private String nome;
    private String email;
    private SerieEscolar serieEscolar;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}