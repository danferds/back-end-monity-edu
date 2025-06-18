package com.pi2.monity_edu.repository;

import com.pi2.monity_edu.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, UUID> {
    Optional<Monitor> findByEmail(String email);
}