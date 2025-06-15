package com.pi2.monity_edu.finder;

import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AlunoFinder {

    private final AlunoRepository alunoRepository;

    public Aluno buscarPorId(UUID id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado."));
    }
}
