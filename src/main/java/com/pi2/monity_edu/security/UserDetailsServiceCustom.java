package com.pi2.monity_edu.security;

import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {

    private final AlunoRepository alunoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Aluno aluno = alunoRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username));

        return new UserDetailsImpl(aluno);
    }

    /**
     * Carrega um usuário pelo seu ID (UUID).
     * Este método será usado pelo filtro JWT para re-autenticar o usuário a cada requisição.
     * @param id O UUID do usuário a ser carregado.
     * @return Os detalhes do usuário encontrado.
     * @throws UsernameNotFoundException se nenhum usuário for encontrado com o ID fornecido.
     */
    public UserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new UserDetailsImpl(aluno);
    }
}