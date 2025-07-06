package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaResponseDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitoriaService {

    private final MonitoriaRepository monitoriaRepository;
    private final MonitorFinder monitorFinder;
    private final MonitoriaMapper monitoriaMapper;
    private final MonitoriaValidation monitoriaValidation;
    private final MaterialComplementarService materialComplementarService;
    private final FinderMonitoria monitoriaFinder;

    @Transactional
    public MonitoriaResponseDTO cadastrarMonitoria(MonitoriaCadastroDTO dto, UUID monitorId) {
        log.info("Iniciando processo de cadastro de monitoria para o monitor ID: {}", monitorId);

        Monitor monitor = monitorFinder.buscarPorId(monitorId);

        monitoriaValidation.validarCadastroMonitoria(dto, monitor);

        Monitoria novaMonitoria = monitoriaMapper.toMonitoria(dto);
        novaMonitoria.setMonitor(monitor);
        novaMonitoria.setStatus(StatusMonitoria.PENDENTE);

        processarMateriais(dto.getArquivos(), novaMonitoria);

        Monitoria monitoriaSalva = monitoriaRepository.save(novaMonitoria);
        log.info("Monitoria cadastrada com sucesso. ID: {}", monitoriaSalva.getId());

        return monitoriaMapper.toMonitoriaResponseDTO(monitoriaSalva);
    }

    public MonitoriaResponseDTO getMonitoriaById(UUID monitorId) {
        log.info("Buscando monitoria com ID: {}", monitorId);

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitorId);

        return monitoriaMapper.toMonitoriaResponseDTO(monitoria);
    }

    @Transactional
    public Boolean cancelarMonitoria(UUID monitorId, UserDetailsImpl userDetails) {
        log.info("Cancelando monitoria com ID: {}", monitorId);


        Monitoria monitoria = monitoriaFinder.buscarPorId(monitorId);

        monitoriaValidation.podeCancelar(monitoria, userDetails);

        monitoria.setStatus(StatusMonitoria.CANCELADA);
        monitoriaRepository.save(monitoria);

        log.info("Monitoria com ID: {} cancelada com sucesso", monitorId);

        return true;
    }

    private void processarMateriais(List<MultipartFile> arquivos, Monitoria monitoria) {
        if (arquivos != null && !arquivos.isEmpty()) {
            monitoria.setMateriais(
                    arquivos.stream()
                            .map(arquivo -> materialComplementarService.criarMaterial(arquivo, monitoria))
                            .collect(Collectors.toList()));
        }
    }
}