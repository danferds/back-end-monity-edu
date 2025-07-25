package com.pi2.monity_edu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "certificados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String nomeArquivo;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitoria_id", nullable = false)
    private Monitoria monitoria;
}