package com.pi2.monity_edu.model;

import java.util.UUID;

/**
 * Interface que define o contrato base para todos os usuários.
 */
public interface Usuario {

    UUID getId();

    void setId(UUID id);

    String getNome();

    void setNome(String nome);

    String getEmail();

    void setEmail(String email);

    String getSenha();

    void setSenha(String senha);

    StatusMonitor getStatus();
}