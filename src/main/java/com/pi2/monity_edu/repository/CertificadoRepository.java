package com.pi2.monity_edu.repository;

import com.pi2.monity_edu.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, UUID>, JpaSpecificationExecutor<Certificado> {
}