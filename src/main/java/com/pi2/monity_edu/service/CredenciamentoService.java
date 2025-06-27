package com.pi2.monity_edu.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi2.monity_edu.dto.CredenciamentoCadastroDTO;
import com.pi2.monity_edu.dto.CredenciamentoResponseDTO;
import com.pi2.monity_edu.factory.CredenciamentoFactory;
import com.pi2.monity_edu.mapper.CredenciamentoMapper;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Credenciamento;
import com.pi2.monity_edu.repository.MonitorRepository;
import com.pi2.monity_edu.repository.CredenciamentoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CredenciamentoService {
  private final CredenciamentoRepository credenciamentoRepository;
  private final CredenciamentoFactory credenciamentoFactory;
  private final CredenciamentoMapper credenciamentoMapper;
  private final MonitorRepository monitorRepository;

  @Transactional
  public CredenciamentoResponseDTO cadastrarVerificacao(CredenciamentoCadastroDTO dto) {
    log.info("Iniciando processo de cadastro de monitor para o usuario: {}", dto.getMonitorId());
    Monitor monitor = monitorRepository.findById(UUID.fromString(dto.getMonitorId()))
        .orElseThrow(() -> new RuntimeException("Monitor não encontrado"));

    Credenciamento credenciamento = credenciamentoFactory.cadastrarCredenciamento(monitor);
    credenciamento.setMonitor(monitor);
    credenciamentoMapper.popularValidacaoDeDTO(dto, credenciamento);

    Credenciamento credenciamentoSalvo = credenciamentoRepository.save(credenciamento);

    log.info("Verificação cadastrada com sucesso. ID: {}", credenciamentoSalvo.getId());
    return credenciamentoMapper.toValidacaoResponseDTO(credenciamentoSalvo);
  }

}
