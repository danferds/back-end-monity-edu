package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.exception.EmailJaCadastradoException;
import com.pi2.monity_edu.repository.AlunoRepository;
import com.pi2.monity_edu.repository.MonitorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CadastroValidation {

    private final AlunoRepository alunoRepository;
    private final MonitorRepository monitorRepository;

    public void verificarSeEmailExiste(String email) {
        if (alunoRepository.findByEmail(email).isPresent() || monitorRepository.findByEmail(email).isPresent()) {
            throw new EmailJaCadastradoException("Este e-mail já está cadastrado na plataforma.");
        }
    }

    public void validarAtualizacaoEmail(String novoEmail, String emailAtual, UUID usuarioId) {
        if (novoEmail != null && !novoEmail.equalsIgnoreCase(emailAtual)) {
            log.info("Tentativa de alteração de e-mail para: {}", novoEmail);

            verificarSeEmailExiste(novoEmail);
        }
    }
}