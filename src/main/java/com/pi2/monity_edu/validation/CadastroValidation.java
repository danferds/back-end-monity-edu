package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.exception.DominioInstitucionalInvalidoException;
import com.pi2.monity_edu.exception.EmailInexistenteException;
import com.pi2.monity_edu.exception.EmailJaCadastradoException;
import com.pi2.monity_edu.repository.AlunoRepository;
import com.pi2.monity_edu.repository.MonitorRepository;
import com.pi2.monity_edu.service.DomainValidationService;
import com.pi2.monity_edu.service.EmailExistenceService;
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
    private final EmailExistenceService emailExistenceService;
    private final DomainValidationService domainValidationService;

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

    public void validarEmailInstitucional(String emailInstitucional) {
        log.info("Iniciando validação completa para o e-mail: {}", emailInstitucional);

        if (!emailExistenceService.verificarExistencia(emailInstitucional)) {
            throw new EmailInexistenteException("O endereço de e-mail institucional fornecido não existe ou não pôde ser verificado.");
        }
        log.info("Verificação de existência da caixa de correio para {} bem-sucedida.", emailInstitucional);

        if (!domainValidationService.isAcademicEmail(emailInstitucional)) {
            throw new DominioInstitucionalInvalidoException("O domínio do e-mail fornecido não é reconhecido como educacional.");
        }
        log.info("Verificação de domínio acadêmico para {} bem-sucedida.", emailInstitucional);
    }
}