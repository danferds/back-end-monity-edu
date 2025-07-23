package com.pi2.monity_edu.dto;

import com.pi2.monity_edu.model.StatusMonitoria;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Value
public class AlunoMonitoriaResponseDTO {
    UUID id;
    String titulo;
    LocalDate data;
    LocalTime horarioInicio;
    LocalTime horarioFim;
    String linkReuniao;
    String materia;
    String topico;
    String descricao;
    StatusMonitoria status;
    String nomeMonitor;
    Double avaliacaoMediaMonitor;
    List<MaterialComplementarResponseDTO> materiais;
}