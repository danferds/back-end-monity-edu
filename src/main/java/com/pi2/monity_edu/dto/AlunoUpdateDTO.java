package com.pi2.monity_edu.dto;

import com.pi2.monity_edu.model.SerieEscolar;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlunoUpdateDTO {

    private String nome;

    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String senha;

    private SerieEscolar serieEscolar;
}