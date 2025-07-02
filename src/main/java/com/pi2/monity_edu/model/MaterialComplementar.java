package com.pi2.monity_edu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "materiais_complementares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialComplementar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String nomeArquivo;

    @Column(nullable = false)
    private String tipoArquivo;

    @Lob
    @Column(nullable = false)
    private byte[] dados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitoria_id", nullable = false)
    private Monitoria monitoria;
}