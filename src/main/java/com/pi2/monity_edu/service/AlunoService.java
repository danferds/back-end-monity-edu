package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.AlunoCadastroDTO;
import com.pi2.monity_edu.dto.AlunoResponseDTO;
import com.pi2.monity_edu.factory.UsuarioFactory;
import com.pi2.monity_edu.mapper.AlunoMapper;
import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.model.Usuario;
import com.pi2.monity_edu.repository.AlunoRepository;
import com.pi2.monity_edu.validation.CadastroValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AlunoMapper alunoMapper;
    private final CadastroValidation cadastroValidation;
    private final UsuarioFactory usuarioFactory;

    @Transactional
    public AlunoResponseDTO cadastrarNovoAluno(AlunoCadastroDTO dto) {
        log.info("Iniciando processo de cadastro para o e-mail: {}", dto.getEmail());

        cadastroValidation.verificarSeEmailExiste(dto.getEmail());

        Usuario usuario = usuarioFactory.criarUsuario("ALUNO");
        Aluno novoAluno = (Aluno) usuario;

        alunoMapper.popularAlunoDeDTO(dto, novoAluno);

        // Codifica a senha.
        novoAluno.setSenha(passwordEncoder.encode(dto.getSenha()));

        Aluno alunoSalvo = alunoRepository.save(novoAluno);

        log.info("Aluno cadastrado com sucesso. ID: {}", alunoSalvo.getId());
        return alunoMapper.toAlunoResponseDTO(alunoSalvo);
    }
}