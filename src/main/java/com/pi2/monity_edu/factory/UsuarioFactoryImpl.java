package com.pi2.monity_edu.factory;

import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFactoryImpl implements UsuarioFactory {

    @Override
    public Usuario criarUsuario(String tipo) {
        if ("ALUNO".equalsIgnoreCase(tipo)) {
            return new Aluno();
        }
        if ("MONITOR".equalsIgnoreCase(tipo)) {
            return new Monitor();
        }
        throw new IllegalArgumentException("Tipo de usuário desconhecido: " + tipo);
    }
}