package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.MonitorCadastroDTO;
import com.pi2.monity_edu.dto.MonitorResponseDTO;
import com.pi2.monity_edu.dto.MonitorUpdateDTO;
import com.pi2.monity_edu.factory.UsuarioFactory;
import com.pi2.monity_edu.finder.MonitorFinder;
import com.pi2.monity_edu.mapper.MonitorMapper;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Usuario;
import com.pi2.monity_edu.repository.MonitorRepository;
import com.pi2.monity_edu.validation.CadastroValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitorService {

    private final MonitorRepository monitorRepository;
    private final PasswordEncoder passwordEncoder;
    private final MonitorMapper monitorMapper;
    private final CadastroValidation cadastroValidation;
    private final UsuarioFactory usuarioFactory;
    private final MonitorFinder monitorFinder;

    @Transactional
    public MonitorResponseDTO cadastrarNovoMonitor(MonitorCadastroDTO dto) {
        log.info("Iniciando processo de cadastro de monitor para o e-mail: {}", dto.getEmail());

        cadastroValidation.verificarSeEmailExiste(dto.getEmail());

        Usuario usuario = usuarioFactory.criarUsuario("MONITOR");
        Monitor novoMonitor = (Monitor) usuario;
        monitorMapper.popularMonitorDeDTO(dto, novoMonitor);

        // Codifica a senha.
        novoMonitor.setSenha(passwordEncoder.encode(dto.getSenha()));

        Monitor monitorSalvo = monitorRepository.save(novoMonitor);

        log.info("Monitor cadastrado com sucesso. ID: {}", monitorSalvo.getId());
        return monitorMapper.toMonitorResponseDTO(monitorSalvo);
    }

    @Transactional
    public MonitorResponseDTO atualizarMonitor(UUID id, MonitorUpdateDTO dto) {
        log.info("Iniciando processo de atualização para o monitor de ID: {}", id);

        Monitor monitor = monitorFinder.buscarPorId(id);

        cadastroValidation.validarAtualizacaoEmail(dto.getEmail(), monitor.getEmail(), monitor.getId());

        monitorMapper.updateMonitorFromDto(dto, monitor);
        processarAtualizacaoSenha(dto, monitor);

        Monitor monitorAtualizado = monitorRepository.save(monitor);
        log.info("Monitor de ID: {} atualizado com sucesso.", monitorAtualizado.getId());

        return monitorMapper.toMonitorResponseDTO(monitorAtualizado);
    }

    @Transactional
    public void excluirMonitor(UUID id) {
        log.warn("Iniciando processo de exclusão para o monitor de ID: {}", id);

        monitorFinder.buscarPorId(id);
        monitorRepository.deleteById(id);

        log.info("Monitor de ID: {} excluído com sucesso.", id);
    }

    private void processarAtualizacaoSenha(MonitorUpdateDTO dto, Monitor monitor) {
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            log.info("Atualizando a senha do monitor de ID: {}", monitor.getId());
            monitor.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
    }
}