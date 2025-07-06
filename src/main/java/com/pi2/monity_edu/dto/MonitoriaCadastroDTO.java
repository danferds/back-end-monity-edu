package com.pi2.monity_edu.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class MonitoriaCadastroDTO {

    @NotBlank(message = "O título não pode ser vazio.")
    private String titulo;

    @NotNull(message = "A data não pode ser vazia.")
    @FutureOrPresent(message = "A data da monitoria não pode ser no passado.")
    private LocalDate data;

    @NotNull(message = "O horário de início não pode ser vazio.")
    private LocalTime horarioInicio;

    @NotNull(message = "O horário de fim não pode ser vazio.")
    private LocalTime horarioFim;

    @NotBlank(message = "O link da reunião não pode ser vazio.")
    @URL(message = "O formato do link da reunião é inválido. Utilize um link válido do Google Meet.")
    private String linkReuniao;

    @NotBlank(message = "A matéria não pode ser vazia.")
    private String materia;

    @NotBlank(message = "O tópico não pode ser vazio.")
    private String topico;

    @NotBlank(message = "A descrição não pode ser vazia.")
    private String descricao;

    private List<MultipartFile> arquivos;
}