package com.pi2.monity_edu.controller;


import com.pi2.monity_edu.dto.AlunoCadastroDTO;
import com.pi2.monity_edu.dto.AlunoResponseDTO;
import com.pi2.monity_edu.dto.AlunoUpdateDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.service.AlunoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<AlunoResponseDTO>> cadastrarAluno(@Valid @RequestBody AlunoCadastroDTO alunoDTO) {
        AlunoResponseDTO alunoCriado = alunoService.cadastrarNovoAluno(alunoDTO);
        return ResponseFactory.created(alunoCriado);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("@authService.checarDonoDoPerfil(authentication.principal, #id)")
    public ResponseEntity<ApiResponse<AlunoResponseDTO>> atualizarAluno(
            @PathVariable UUID id,
            @Valid @RequestBody AlunoUpdateDTO alunoUpdateDTO) {

        AlunoResponseDTO alunoAtualizado = alunoService.atualizarAluno(id, alunoUpdateDTO);
        return ResponseFactory.success(alunoAtualizado);
    }
}