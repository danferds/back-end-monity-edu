package com.pi2.monity_edu.repository;

import com.pi2.monity_edu.model.Avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {

    boolean existsByAlunoIdAndMonitoriaId(UUID alunoId, UUID monitoriaId);

    List<Avaliacao> findAllByMonitorId(UUID monitorId);
}