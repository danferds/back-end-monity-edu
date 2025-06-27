package com.pi2.monity_edu.factory;

import java.util.Date;

import org.springframework.stereotype.Component;
import com.pi2.monity_edu.model.Credenciamento;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.StatusMonitor;

@Component
public class CredenciamentoFactoryImpl implements CredenciamentoFactory {
  @Override
  public Credenciamento cadastrarCredenciamento(Monitor monitor) {
    Credenciamento credenciamento = new Credenciamento();
    credenciamento.setMonitor(monitor);
    credenciamento.setStatus(StatusMonitor.PENDENTE);
    credenciamento.setDataSubmissao(new Date());

    return credenciamento;
  }
}