package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.AvaliacaoDTO;
import com.pi2.monity_edu.finder.AlunoFinder;
import com.pi2.monity_edu.finder.FinderMonitoria;
import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.model.Avaliacao;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.repository.AvaliacaoRepository;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.validation.AvaliacaoValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final FinderMonitoria monitoriaFinder;
    private final AlunoFinder alunoFinder;
    private final AvaliacaoValidation avaliacaoValidation;

    @Transactional
    public void avaliarMonitoria(UUID monitoriaId, UserDetailsImpl userDetails, AvaliacaoDTO avaliacaoDTO) {
        if (!(userDetails.getUsuario() instanceof Aluno)) {
            throw new AccessDeniedException("Apenas alunos podem registrar avaliações.");
        }

        UUID alunoId = userDetails.getUsuario().getId();
        log.info("Iniciando processo de avaliação da monitoria ID {} pelo aluno ID {}", monitoriaId, alunoId);

        Monitoria monitoria = monitoriaFinder.buscarPorId(monitoriaId);
        Aluno aluno = alunoFinder.buscarPorId(alunoId);

        avaliacaoValidation.validarAvaliacao(monitoria, aluno);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setMonitoria(monitoria);
        avaliacao.setAluno(aluno);
        avaliacao.setMonitor(monitoria.getMonitor());

        avaliacaoRepository.save(avaliacao);
        log.info("Avaliação da monitoria ID {} pelo aluno ID {} salva com sucesso", monitoriaId, alunoId);
    }

    @Transactional(readOnly = true)
    public Double getMediaAvaliacoesMonitor(UUID monitorId) {
        log.info("Calculando a média de avaliações para o monitor ID {}", monitorId);
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAllByMonitorId(monitorId);

        if (avaliacoes.isEmpty()) {
            return 0.0;
        }

        return avaliacoes.stream()
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }
}