package com.pi2.monity_edu.dto;

import com.pi2.monity_edu.model.StatusMonitor;
import lombok.Value;
import java.util.UUID;

@Value
public class MonitorResponseDTO {
    UUID id;
    String nome;
    String email;
    StatusMonitor status;
}