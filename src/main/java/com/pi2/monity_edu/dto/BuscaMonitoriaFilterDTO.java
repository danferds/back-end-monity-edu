package com.pi2.monity_edu.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class BuscaMonitoriaFilterDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    private String materia;

    private String topico;
}