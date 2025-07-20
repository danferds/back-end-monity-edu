package com.pi2.monity_edu.repository.specification;

import com.pi2.monity_edu.dto.CertificadoFilterDTO;
import com.pi2.monity_edu.model.Certificado;
import com.pi2.monity_edu.model.Monitoria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CertificadoSpecification {

    /**
     * Cria uma Specification para filtrar os certificados de um monitor específico.
     * 
     * @param filtro    DTO com os filtros (titulo).
     * @param monitorId O ID do monitor.
     * @return Uma Specification para ser usada com JpaSpecificationExecutor.
     */
    public Specification<Certificado> getCertificados(CertificadoFilterDTO filtro, UUID monitorId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("monitoria").get("monitor").get("id"), monitorId));

            if (filtro.getTitulo() != null && !filtro.getTitulo().isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("monitoria").get("titulo")),
                        "%" + filtro.getTitulo().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}