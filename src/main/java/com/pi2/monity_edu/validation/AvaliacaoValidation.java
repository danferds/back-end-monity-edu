package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.exception.AvaliacaoException;
import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.model.StatusMonitoria;
import com.pi2.monity_edu.repository.AvaliacaoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AvaliacaoValidation {

    private final AvaliacaoRepository avaliacaoRepository;

    public void validarAvaliacao(Monitoria monitoria, Aluno aluno) {

        if (monitoria.getStatus() != StatusMonitoria.REALIZADA) {
            throw new AvaliacaoException("Só é possível avaliar monitorias que já foram realizadas.");
        }

        if (!monitoria.getAlunosInscritos().contains(aluno)) {
            throw new AvaliacaoException("Você não pode avaliar uma monitoria na qual não estava inscrito.");
        }

        if (avaliacaoRepository.existsByAlunoIdAndMonitoriaId(aluno.getId(), monitoria.getId())) {
            throw new AvaliacaoException("Você já avaliou esta monitoria.");
        }
    }
}