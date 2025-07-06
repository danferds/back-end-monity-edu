package com.pi2.monity_edu.finder;

import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.repository.MonitoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FinderMonitoria {
    private final MonitoriaRepository monitoriaRepository;

    public Monitoria buscarPorId(UUID id) {
        return monitoriaRepository.
                findById(id).orElseThrow(() -> new IllegalArgumentException("Monitoria não encontrada"));

    }
}
