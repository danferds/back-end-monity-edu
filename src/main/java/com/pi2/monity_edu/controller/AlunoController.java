package com.pi2.monity_edu.controller;


import com.pi2.monity_edu.dto.AlunoCadastroDTO;
import com.pi2.monity_edu.dto.AlunoResponseDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.service.AlunoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}