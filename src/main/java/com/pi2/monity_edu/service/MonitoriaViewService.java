package com.pi2.monity_edu.service;

import com.pi2.monity_edu.finder.FinderMonitoria;
import com.pi2.monity_edu.mapper.MonitoriaMapper;
import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.validation.MonitoriaValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitoriaViewService {

    private final FinderMonitoria monitoriaFinder;
    private final MonitoriaValidation monitoriaValidation;
    private final MonitoriaMapper monitoriaMapper;

    @Transactional(readOnly = true)
    public Object getMonitoriaViewById(UUID monitoriaId, UserDetailsImpl userDetails) {
        log.info("Determinando a visão correta para a monitoria ID: {}", monitoriaId);
        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);

        if (userDetails.getUsuario() instanceof Aluno) {
            monitoriaValidation.validarVisualizacaoAluno(monitoria);
            return monitoriaMapper.toAlunoMonitoriaResponseDTO(monitoria);
        }

        return monitoriaMapper.toMonitoriaResponseDTO(monitoria);
    }
}