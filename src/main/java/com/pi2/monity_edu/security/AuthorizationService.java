package com.pi2.monity_edu.security;

import com.pi2.monity_edu.model.Aluno;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service("authService")
public class AuthorizationService {

    /**
     * Verifica se o usuário autenticado é o dono do perfil que está sendo acessado.
     * @param userDetails O principal do usuário autenticado.
     * @param idDoRecurso O ID do recurso (perfil do aluno) que está sendo acessado.
     * @return true se o usuário for o dono, false caso contrário.
     */
    public boolean checarDonoDoPerfil(UserDetailsImpl userDetails, UUID idDoRecurso) {
        if (userDetails == null) {
            return false;
        }
        Aluno alunoAutenticado = userDetails.getAluno();
        return alunoAutenticado.getId().equals(idDoRecurso);
    }
}