package com.pi2.monity_edu.repository;

import com.pi2.monity_edu.model.MaterialComplementar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaterialComplementarRepository extends JpaRepository<MaterialComplementar, UUID> {
}