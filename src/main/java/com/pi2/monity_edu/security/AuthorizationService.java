package com.pi2.monity_edu.security;

import com.pi2.monity_edu.model.Usuario;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service("authService")
public class AuthorizationService {

    /**
     * Verifica se o usuário autenticado é o dono do perfil que está sendo acessado.
     * @param userDetails O principal do usuário autenticado.
     * @param idDoRecurso O ID do usuário que está sendo acessado.
     * @return true se o usuário for o dono, false caso contrário.
     */
    public boolean checarDonoDoPerfil(UserDetailsImpl userDetails, UUID idDoRecurso) {
        if (userDetails == null) {
            return false;
        }
        Usuario userAutenticado = userDetails.getUsuario();
        return userAutenticado.getId().equals(idDoRecurso);
    }
}