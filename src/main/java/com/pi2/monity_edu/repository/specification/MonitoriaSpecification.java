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
     * Cria uma Specification para filtrar as monitorias de um monitor específico.
     * @param filtro DTO com os filtros (status, data, tópico).
     * @param monitorId O ID do monitor.
     * @return Uma Specification para ser usada com JpaSpecificationExecutor.
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