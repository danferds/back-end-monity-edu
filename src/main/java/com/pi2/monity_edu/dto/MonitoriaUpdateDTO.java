package com.pi2.monity_edu.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class MonitoriaUpdateDTO {

    private String titulo;

    @FutureOrPresent(message = "A data da monitoria não pode ser no passado.")
    private LocalDate data;

    private LocalTime horarioInicio;

    private LocalTime horarioFim;

    @URL(message = "O formato do link da reunião é inválido. Utilize um link válido do Google Meet.")
    private String linkReuniao;

    private String materia;

    private String topico;

    private String descricao;

    private List<MultipartFile> novosArquivos;

    private List<UUID> materiaisParaRemover;
}