package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.MonitorCadastroDTO;
import com.pi2.monity_edu.dto.MonitorResponseDTO;
import com.pi2.monity_edu.factory.UsuarioFactory;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitorService {

    private final MonitorRepository monitorRepository;
    private final PasswordEncoder passwordEncoder;
    private final MonitorMapper monitorMapper;
    private final CadastroValidation cadastroValidation;
    private final UsuarioFactory usuarioFactory;

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
}