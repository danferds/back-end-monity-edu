package com.pi2.monity_edu.repository.specification;

import com.pi2.monity_edu.dto.AlunoMonitoriaFilterDTO;
import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.model.Monitoria;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AlunoMonitoriaSpecification {

    /**
     * Cria uma especificação para consultar monitorias em que o aluno está inscrito,
     * aplicando filtros opcionais por status, data, matéria e tópico.
     *
     * @param alunoId ID do aluno logado.
     * @param filter Filtros opcionais da consulta.
     * @return Especificação JPA com os critérios aplicados.
     */
    public Specification<Monitoria> getInscricoes(UUID alunoId, AlunoMonitoriaFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Monitoria, Aluno> alunoJoin = root.join("alunosInscritos");

            predicates.add(criteriaBuilder.equal(alunoJoin.get("id"), alunoId));

            if (filter.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }

            if (filter.getData() != null) {
                predicates.add(criteriaBuilder.equal(root.get("data"), filter.getData()));
            }

            if (StringUtils.hasText(filter.getMateria())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("materia")),
                        "%" + filter.getMateria().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(filter.getTopico())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("topico")),
                        "%" + filter.getTopico().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}