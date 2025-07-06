package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaResponseDTO;
import com.pi2.monity_edu.dto.MonitoriaUpdateDTO;
import com.pi2.monity_edu.finder.FinderMonitoria;
import com.pi2.monity_edu.finder.MonitorFinder;
import com.pi2.monity_edu.mapper.MonitoriaMapper;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.model.StatusMonitoria;
import com.pi2.monity_edu.repository.MonitoriaRepository;
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
public class MonitoriaService {

    private final MonitoriaRepository monitoriaRepository;
    private final MonitorFinder monitorFinder;
    private final MonitoriaMapper monitoriaMapper;
    private final MonitoriaValidation monitoriaValidation;
    private final FinderMonitoria monitoriaFinder;
    private final MonitoriaMaterialService monitoriaMaterialService;

    @Transactional
    public MonitoriaResponseDTO cadastrarMonitoria(MonitoriaCadastroDTO dto, UUID monitorId) {
        log.info("Iniciando processo de cadastro de monitoria para o monitor ID: {}", monitorId);

        Monitor monitor = monitorFinder.buscarPorId(monitorId);
        monitoriaValidation.validarCadastroMonitoria(dto, monitor);

        Monitoria novaMonitoria = monitoriaMapper.toMonitoria(dto);
        novaMonitoria.setMonitor(monitor);
        novaMonitoria.setStatus(StatusMonitoria.PENDENTE);
        monitoriaMaterialService.processarNovosMateriais(dto.getArquivos(), novaMonitoria);

        Monitoria monitoriaSalva = monitoriaRepository.save(novaMonitoria);

        log.info("Monitoria cadastrada com sucesso. ID: {}", monitoriaSalva.getId());

        return monitoriaMapper.toMonitoriaResponseDTO(monitoriaSalva);
    }

    public MonitoriaResponseDTO getMonitoriaById(UUID monitoriaId) {
        log.info("Buscando monitoria com ID: {}", monitoriaId);

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);

        return monitoriaMapper.toMonitoriaResponseDTO(monitoria);
    }

    @Transactional
    public Boolean cancelarMonitoria(UUID monitoriaId, UserDetailsImpl userDetails) {
        log.info("Cancelando monitoria com ID: {}", monitoriaId);

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);
        monitoriaValidation.podeCancelar(monitoria, userDetails);
        monitoria.setStatus(StatusMonitoria.CANCELADA);

        monitoriaRepository.save(monitoria);

        log.info("Monitoria com ID: {} cancelada com sucesso", monitoriaId);

        return true;
    }

    @Transactional
    public MonitoriaResponseDTO atualizarMonitoria(UUID monitoriaId, MonitoriaUpdateDTO dto, UserDetailsImpl userDetails) {
        log.info("Iniciando processo de atualização para a monitoria de ID: {}", monitoriaId);

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);
        monitoriaValidation.validarEdicaoMonitoria(dto, monitoria, userDetails);
        monitoriaMapper.updateMonitoriaFromDto(dto, monitoria);
        monitoriaMaterialService.atualizarMateriaisDaMonitoria(dto, monitoria);

        Monitoria monitoriaAtualizada = monitoriaRepository.save(monitoria);

        log.info("Monitoria de ID: {} atualizada com sucesso.", monitoriaAtualizada.getId());

        return monitoriaMapper.toMonitoriaResponseDTO(monitoriaAtualizada);
    }
}