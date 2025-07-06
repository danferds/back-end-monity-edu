package com.pi2.monity_edu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDTO {

    private String token;
    private UUID id;
    private String nome;
    private String email;
    private String userType; // "ALUNO" ou "MONITOR"
    private String expirationTime; // data de expiração do token
    private String statusMonitor;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}