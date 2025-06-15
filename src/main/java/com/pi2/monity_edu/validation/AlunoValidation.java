package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.exception.EmailJaCadastradoException;
import com.pi2.monity_edu.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CadastroValidation {

    private final AlunoRepository alunoRepository;

    public void verificarSeEmailExiste(String email) {
        if (alunoRepository.findByEmail(email).isPresent()) {
            throw new EmailJaCadastradoException("O e-mail informado já está em uso: " + email);
        }
    }

    public void verificarSeEmailJaEstaEmUsoPorOutroAluno(String email, UUID idDoAlunoAtual) {
        alunoRepository.findByEmail(email).ifPresent(alunoEncontrado -> {
            if (!alunoEncontrado.getId().equals(idDoAlunoAtual)) {
                throw new EmailJaCadastradoException("O e-mail informado já está em uso: " + email);
            }
        });
    }
}