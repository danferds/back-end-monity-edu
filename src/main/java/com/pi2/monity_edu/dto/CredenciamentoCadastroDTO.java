package com.pi2.monity_edu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CredenciamentoCadastroDTO {

    @NotBlank(message = "Informação imcompleta")
    private String monitorId;

    @NotBlank(message = "A instituição de ensino não pode ser vazia.")
    private String instituicaoEnsino;

    @NotBlank(message = "O curso não pode ser vazio.")
    private String curso;

    @NotBlank(message = "O período atual não pode ser vazio.")
    private String periodoAtual;

    @Email(message = "Formato de e-mail institucional inválido.")
    @NotBlank(message = "O e-mail institucional não pode ser vazio.")
    private String emailInstitucional;
}