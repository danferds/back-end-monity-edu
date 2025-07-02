package com.pi2.monity_edu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "monitorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monitoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horarioInicio;

    @Column(nullable = false)
    private LocalTime horarioFim;

    @Column(nullable = false)
    private String linkReuniao;

    @Column(nullable = false)
    private String materia;

    @Column(nullable = false)
    private String topico;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monitor_id", nullable = false)
    private Monitor monitor;

    @ManyToMany
    @JoinTable(
            name = "monitoria_alunos",
            joinColumns = @JoinColumn(name = "monitoria_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunosInscritos = new ArrayList<>();

    @OneToMany(mappedBy = "monitoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialComplementar> materiais = new ArrayList<>();
}