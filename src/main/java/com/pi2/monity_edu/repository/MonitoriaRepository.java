package com.pi2.monity_edu.repository;

import com.pi2.monity_edu.model.Monitoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MonitoriaRepository extends JpaRepository<Monitoria, UUID>, JpaSpecificationExecutor<Monitoria> {
}