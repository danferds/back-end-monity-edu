package com.pi2.monity_edu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "avaliacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private int nota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitoria_id", nullable = false)
    private Monitoria monitoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitor_id", nullable = false)
    private Monitor monitor;

    @Column(name = "data_avaliacao", nullable = false)
    private LocalDateTime dataAvaliacao;

    @PrePersist
    public void prePersist() {
        dataAvaliacao = LocalDateTime.now();
    }
}