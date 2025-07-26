package com.pi2.monity_edu.repository.specification;

import com.pi2.monity_edu.dto.BuscaMonitoriaFilterDTO;
import com.pi2.monity_edu.dto.MonitoriaFilterDTO;
import com.pi2.monity_edu.model.Monitoria;

import com.pi2.monity_edu.model.StatusMonitoria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MonitoriaSpecification {

    /**
     * Cria uma especificação para consultar monitorias vinculadas a um monitor,
     * aplicando filtros opcionais por status, data e tópico.
     *
     * @param filtro Filtros opcionais da consulta.
     * @param monitorId ID do monitor logado.
     * @return Especificação JPA com os critérios aplicados.
     */
    public Specification<Monitoria> getMonitorias(MonitoriaFilterDTO filtro, UUID monitorId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("monitor").get("id"), monitorId));

            if (filtro.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filtro.getStatus()));
            }

            if (filtro.getData() != null) {
                predicates.add(criteriaBuilder.equal(root.get("data"), filtro.getData()));
            }

            if (filtro.getTopico() != null && !filtro.getTopico().isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("topico")),
                        "%" + filtro.getTopico().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Cria uma Specification para a busca de monitorias disponíveis para os alunos se inscreverem.
     * @param filtro DTO com os filtros de busca (data, matéria, tópico).
     * @return Especificação JPA com os critérios aplicados.
     */
    public Specification<Monitoria> getMonitoriasDisponiveis(BuscaMonitoriaFilterDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("status"), StatusMonitoria.PENDENTE));

            if (filtro.getData() != null) {
                predicates.add(criteriaBuilder.equal(root.get("data"), filtro.getData()));
            }

            if (StringUtils.hasText(filtro.getMateria())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("materia")),
                        "%" + filtro.getMateria().toLowerCase() + "%"));
            }

            if (StringUtils.hasText(filtro.getTopico())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("topico")),
                        "%" + filtro.getTopico().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}