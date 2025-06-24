package com.pi2.monity_edu.finder;

import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.repository.MonitorRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MonitorFinder {

    private final MonitorRepository monitorRepository;

    public Monitor buscarPorId(UUID id) {
        return monitorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Monitor não encontrado."));
    }
}