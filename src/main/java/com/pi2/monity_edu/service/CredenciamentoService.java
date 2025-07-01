package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.CredenciamentoCadastroDTO;
import com.pi2.monity_edu.dto.CredenciamentoResponseDTO;
import com.pi2.monity_edu.exception.DominioInstitucionalInvalidoException;
import com.pi2.monity_edu.exception.EmailInexistenteException;
import com.pi2.monity_edu.mapper.CredenciamentoMapper;
import com.pi2.monity_edu.model.Credenciamento;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.StatusMonitor;
import com.pi2.monity_edu.repository.CredenciamentoRepository;
import com.pi2.monity_edu.repository.MonitorRepository;
import com.pi2.monity_edu.validation.CadastroValidation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CredenciamentoService {

  private final CredenciamentoRepository credenciamentoRepository;
  private final CredenciamentoMapper credenciamentoMapper;
  private final MonitorRepository monitorRepository;
  private final CadastroValidation cadastroValidation;

  public CredenciamentoResponseDTO processarCredenciamento(CredenciamentoCadastroDTO dto) {
    log.info("Iniciando processo de credenciamento para o monitor: {}", dto.getMonitorId());

    try {
      cadastroValidation.validarEmailInstitucional(dto.getEmailInstitucional());
      return aprovar(dto);

    } catch (DominioInstitucionalInvalidoException | EmailInexistenteException e) {
      reprovar(dto.getMonitorId());
      throw e;
    }
  }

  @Transactional
  public CredenciamentoResponseDTO aprovar(CredenciamentoCadastroDTO dto) {
    Monitor monitor = monitorRepository.findById(UUID.fromString(dto.getMonitorId()))
            .orElseThrow(() -> new EntityNotFoundException("Monitor não encontrado"));

    monitor.setStatus(StatusMonitor.APROVADO);
    monitorRepository.save(monitor);
    log.info("Monitor {} APROVADO após validação de e-mail.", monitor.getId());

    Credenciamento credenciamento = new Credenciamento();
    credenciamento.setMonitor(monitor);
    credenciamentoMapper.popularValidacaoDeDTO(dto, credenciamento);
    credenciamento.setStatus(StatusMonitor.APROVADO);
    credenciamento.setDataSubmissao(new Date());

    Credenciamento credenciamentoSalvo = credenciamentoRepository.save(credenciamento);
    log.info("Registro de credenciamento ID {} salvo com sucesso.", credenciamentoSalvo.getId());

    return credenciamentoMapper.toValidacaoResponseDTO(credenciamentoSalvo);
  }

  @Transactional
  public void reprovar(String monitorId) {
    Monitor monitor = monitorRepository.findById(UUID.fromString(monitorId))
            .orElseThrow(() -> new EntityNotFoundException("Monitor não encontrado"));

    monitor.setStatus(StatusMonitor.REPROVADO);
    monitorRepository.save(monitor);
    log.warn("Monitor {} REPROVADO devido a e-mail institucional inválido.", monitor.getId());
  }
}