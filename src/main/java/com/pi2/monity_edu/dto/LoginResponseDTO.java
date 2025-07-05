package com.pi2.monity_edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.pi2.monity_edu.model.StatusMonitor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private String token;
    private UUID id;
    private String nome;
    private String email;
    private String userType; // "ALUNO" ou "MONITOR"
    private String expirationTime; // data de expiração do token
    private StatusMonitor status;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}