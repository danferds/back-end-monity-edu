package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.dto.AlunoUpdateDTO;
import com.pi2.monity_edu.exception.EmailJaCadastradoException;
import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AlunoValidation {

    private final AlunoRepository alunoRepository;

    public void verificarSeEmailExiste(String email) {
        if (alunoRepository.findByEmail(email).isPresent()) {
            throw new EmailJaCadastradoException("O e-mail informado já está em uso.");
        }
    }
}