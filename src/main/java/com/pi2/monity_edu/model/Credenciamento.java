package com.pi2.monity_edu.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "credenciamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Credenciamento {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false, unique = true, columnDefinition = "UUID")
  private UUID id;

  @Column(name = "instituicao_ensino", nullable = false)
  private String instituicaoEnsino;

  @Column(nullable = false)
  private String curso;

  @Column(name = "periodo_atual", nullable = false)
  private String periodoAtual;

  @Column(name = "email_institucional", nullable = true)
  private String emailInstitucional;

  @Column(name = "data_submissao", nullable = false)
  private Date dataSubmissao;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatusMonitor status = StatusMonitor.PENDENTE;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "monitor_id", nullable = false)
  private Monitor monitor;
}