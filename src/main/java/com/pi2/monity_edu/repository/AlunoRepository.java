package com.pi2.monity_edu.repository;


import com.pi2.monity_edu.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Optional<Aluno> findByEmail(String email);
}
