package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaFilterDTO;
import com.pi2.monity_edu.dto.MonitoriaResponseDTO;
import com.pi2.monity_edu.dto.MonitoriaUpdateDTO;
import com.pi2.monity_edu.finder.AlunoFinder;
import com.pi2.monity_edu.finder.FinderMonitoria;
import com.pi2.monity_edu.finder.MonitorFinder;
import com.pi2.monity_edu.mapper.MonitoriaMapper;
import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.model.StatusMonitoria;
import com.pi2.monity_edu.repository.MonitoriaRepository;
import com.pi2.monity_edu.repository.specification.MonitoriaSpecification;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.validation.MonitoriaValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitoriaService {

    private final MonitoriaRepository monitoriaRepository;
    private final MonitorFinder monitorFinder;
    private final AlunoFinder alunoFinder;
    private final MonitoriaMapper monitoriaMapper;
    private final MonitoriaValidation monitoriaValidation;
    private final FinderMonitoria monitoriaFinder;
    private final MonitoriaMaterialService monitoriaMaterialService;
    private final MonitoriaSpecification monitoriaSpecification;
    private final MonitoriaViewService monitoriaViewService;

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

    @Transactional
    public Object getMonitoriaById(UUID monitoriaId, UserDetailsImpl userDetails) {
        log.info("Delegando a busca da visão da monitoria com ID: {}", monitoriaId);
        return monitoriaViewService.getMonitoriaViewById(monitoriaId, userDetails);
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
    public Boolean marcarMonitoriaComoRealizada(UUID monitoriaId, UserDetailsImpl userDetails) {
        log.info("Marcar monitoria como realizada com ID: {}", monitoriaId);

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);
        monitoriaValidation.podeMarcarComoRealizada(monitoria, userDetails);
        monitoria.setStatus(StatusMonitoria.REALIZADA);

        monitoriaRepository.save(monitoria);

        log.info("Monitoria com ID: {} marcada como realizada com sucesso", monitoriaId);

        return true;
    }

    @Transactional
    public MonitoriaResponseDTO atualizarMonitoria(UUID monitoriaId, MonitoriaUpdateDTO dto,
            UserDetailsImpl userDetails) {
        log.info("Iniciando processo de atualização para a monitoria de ID: {}", monitoriaId);

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);
        monitoriaValidation.validarEdicaoMonitoria(dto, monitoria, userDetails);
        monitoriaMapper.updateMonitoriaFromDto(dto, monitoria);
        monitoriaMaterialService.atualizarMateriaisDaMonitoria(dto, monitoria);

        Monitoria monitoriaAtualizada = monitoriaRepository.save(monitoria);

        log.info("Monitoria de ID: {} atualizada com sucesso.", monitoriaAtualizada.getId());

        return monitoriaMapper.toMonitoriaResponseDTO(monitoriaAtualizada);
    }

    @Transactional(readOnly = true)
    public List<MonitoriaResponseDTO> listarMonitorias(MonitoriaFilterDTO filter, UserDetailsImpl userDetails) {

        UUID monitorId = userDetails.getUsuario().getId();

        log.info("Buscando monitorias para o monitor ID: {} com os filtros: {}", monitorId, filter);

        Specification<Monitoria> specificacao = monitoriaSpecification.getMonitorias(filter, monitorId);
        List<Monitoria> monitorias = monitoriaRepository.findAll(specificacao, Sort.by("data", "horarioInicio"));

        return monitorias.stream()
                .map(monitoriaMapper::toMonitoriaResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void inscreverAlunoNaMonitoria(UUID monitoriaId, UserDetailsImpl userDetails) {

        log.info("Iniciando processo de inscrição na monitoria ID: {} para o aluno ID: {}", monitoriaId,
                userDetails.getUsuario().getId());

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);
        Aluno aluno = alunoFinder.buscarPorId(userDetails.getUsuario().getId());

        monitoriaValidation.validarInscricao(monitoria, aluno);

        monitoria.getAlunosInscritos().add(aluno);
        monitoriaRepository.save(monitoria);

        log.info("Aluno ID: {} inscrito com sucesso na monitoria ID: {}", aluno.getId(), monitoria.getId());
    }

    @Transactional
    public void cancelarInscricao(UUID monitoriaId, UserDetailsImpl userDetails) {

        log.info("Iniciando processo de cancelamento de inscrição na monitoria ID: {} para o aluno ID: {}", monitoriaId,
                userDetails.getUsuario().getId());

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);
        Aluno aluno = alunoFinder.buscarPorId(userDetails.getUsuario().getId());

        monitoriaValidation.validarCancelamentoInscricao(monitoria, aluno);

        monitoria.getAlunosInscritos().remove(aluno);
        monitoriaRepository.save(monitoria);

        log.info("Inscrição do aluno ID: {} na monitoria ID: {} cancelada com sucesso", aluno.getId(), monitoria.getId());
    }
}