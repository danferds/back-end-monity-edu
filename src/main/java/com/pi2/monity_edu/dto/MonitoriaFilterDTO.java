package com.pi2.monity_edu.dto;

import com.pi2.monity_edu.model.StatusMonitoria;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MonitoriaFilterDTO {

    private StatusMonitoria status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    private String topico;
}