package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.AlunoCadastroDTO;
import com.pi2.monity_edu.dto.AlunoResponseDTO;
import com.pi2.monity_edu.dto.AlunoUpdateDTO;
import com.pi2.monity_edu.factory.UsuarioFactory;
import com.pi2.monity_edu.finder.AlunoFinder;
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

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AlunoMapper alunoMapper;
    private final CadastroValidation cadastroValidation;
    private final UsuarioFactory usuarioFactory;
    private final AlunoFinder alunoFinder;

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

    @Transactional
    public AlunoResponseDTO atualizarAluno(UUID id, AlunoUpdateDTO dto) {
        log.info("Iniciando processo de atualização para o aluno de ID: {}", id);

        Aluno aluno = alunoFinder.buscarPorId(id);

        //cadastroValidation.verificarSeEmailExiste(dto.getEmail());

        cadastroValidation.validarAtualizacaoEmail(dto.getEmail(), aluno.getEmail(), aluno.getId());

        alunoMapper.updateAlunoFromDto(dto, aluno);
        processarAtualizacaoSenha(dto, aluno);

        Aluno alunoAtualizado = alunoRepository.save(aluno);
        log.info("Aluno de ID: {} atualizado com sucesso.", alunoAtualizado.getId());

        return alunoMapper.toAlunoResponseDTO(alunoAtualizado);
    }

    @Transactional
    public void excluirAluno(UUID id) {
        log.warn("Iniciando processo de exclusão para o aluno de ID: {}", id);

        alunoFinder.buscarPorId(id);
        alunoRepository.deleteById(id);

        log.info("Aluno de ID: {} excluído com sucesso.", id);
    }

    private void processarAtualizacaoSenha(AlunoUpdateDTO dto, Aluno aluno) {
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            log.info("Atualizando a senha do aluno com ID: {}", aluno.getId());
            aluno.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
    }
}