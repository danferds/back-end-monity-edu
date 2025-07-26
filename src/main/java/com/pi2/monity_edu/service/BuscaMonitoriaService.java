package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.AlunoMonitoriaResponseDTO;
import com.pi2.monity_edu.dto.BuscaMonitoriaFilterDTO;
import com.pi2.monity_edu.mapper.MonitoriaMapper;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.repository.MonitoriaRepository;
import com.pi2.monity_edu.repository.specification.MonitoriaSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuscaMonitoriaService {

    private final MonitoriaRepository monitoriaRepository;
    private final MonitoriaSpecification monitoriaSpecification;
    private final MonitoriaMapper monitoriaMapper;
    private final AvaliacaoService avaliacaoService;

    @Transactional(readOnly = true)
    public List<AlunoMonitoriaResponseDTO> buscarMonitoriasDisponiveis(BuscaMonitoriaFilterDTO filter) {
        log.info("Iniciando busca de monitorias disponíveis com filtros: {}", filter);

        Specification<Monitoria> spec = monitoriaSpecification.getMonitoriasDisponiveis(filter);

        List<Monitoria> monitorias = monitoriaRepository.findAll(spec, Sort.by("data", "horarioInicio"));

        return monitorias.stream()
                .map(monitoria -> {
                    Double media = avaliacaoService.getMediaAvaliacoesMonitor(monitoria.getMonitor().getId());
                    return monitoriaMapper.toAlunoMonitoriaResponseDTO(monitoria, media);
                })
                .collect(Collectors.toList());
    }
}