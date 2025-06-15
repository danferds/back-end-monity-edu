package com.pi2.monity_edu.factory;

import com.pi2.monity_edu.model.Usuario;

public interface UsuarioFactory {
    Usuario criarUsuario(String tipo);
}
