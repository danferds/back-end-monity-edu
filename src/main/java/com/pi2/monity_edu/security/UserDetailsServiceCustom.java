package com.pi2.monity_edu.security;

import com.pi2.monity_edu.model.Usuario;
import com.pi2.monity_edu.repository.AlunoRepository;
import com.pi2.monity_edu.repository.MonitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {

    private final AlunoRepository alunoRepository;
    private final MonitorRepository monitorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = alunoRepository.findByEmail(username).map(aluno -> (Usuario) aluno);

        if (usuarioOpt.isEmpty()) {
            usuarioOpt = monitorRepository.findByEmail(username).map(monitor -> (Usuario) monitor);
        }

        Usuario usuario = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        return new UserDetailsImpl(usuario);
    }

    /**
     * Carrega um usuário pelo seu ID (UUID).
     * Este método será usado pelo filtro JWT para re-autenticar o usuário a cada requisição.
     * @param id O UUID do usuário a ser carregado.
     * @return Os detalhes do usuário encontrado.
     * @throws UsernameNotFoundException se nenhum usuário for encontrado com o ID fornecido.
     */

    public UserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = alunoRepository.findById(id).map(aluno -> (Usuario) aluno);

        if (usuarioOpt.isEmpty()) {
            usuarioOpt = monitorRepository.findById(id).map(monitor -> (Usuario) monitor);
        }

        Usuario usuario = usuarioOpt.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new UserDetailsImpl(usuario);
    }
}