package com.pi2.monity_edu.repository.specification;

import com.pi2.monity_edu.dto.MonitoriaFilterDTO;
import com.pi2.monity_edu.model.Monitoria;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;

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
}