package com.pi2.monity_edu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MonitorCadastroDTO {

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @Email(message = "Formato de e-mail inválido.")
    @NotBlank(message = "O e-mail não pode ser vazio.")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String senha;
}