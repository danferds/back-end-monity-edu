package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.exception.EmailJaCadastradoException;
import com.pi2.monity_edu.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastroValidation {

    private final AlunoRepository alunoRepository;

    public void verificarSeEmailExiste(String email) {
        if (alunoRepository.findByEmail(email).isPresent()) {
            throw new EmailJaCadastradoException("O e-mail informado já está em uso: " + email);
        }
    }
}