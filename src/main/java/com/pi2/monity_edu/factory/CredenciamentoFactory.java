package com.pi2.monity_edu.factory;

import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Credenciamento;

public interface CredenciamentoFactory {
    Credenciamento cadastrarCredenciamento(Monitor monitor);
}
