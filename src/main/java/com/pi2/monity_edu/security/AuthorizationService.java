package com.pi2.monity_edu.security;

import com.pi2.monity_edu.model.Usuario;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("authService")
public class AuthorizationService {

    private static final String MSG_ACESSO_NEGADO = "Acesso negado.";

    public void checarDonoDoPerfil(UserDetailsImpl userDetails, UUID idDoRecurso) {
        if (userDetails == null) {
            throw new AccessDeniedException(MSG_ACESSO_NEGADO);
        }

        Usuario usuarioAutenticado = userDetails.getUsuario();

        if (!usuarioAutenticado.getId().equals(idDoRecurso)) {
            throw new AccessDeniedException(MSG_ACESSO_NEGADO);
        }
    }
}