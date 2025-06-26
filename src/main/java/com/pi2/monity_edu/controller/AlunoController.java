package com.pi2.monity_edu.controller;


import com.pi2.monity_edu.dto.*;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.security.AuthorizationService;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.service.AlunoService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;
    private final AuthorizationService authorizationService;

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<AlunoResponseDTO>> cadastrarAluno(@Valid @RequestBody AlunoCadastroDTO alunoDTO) {
        AlunoResponseDTO alunoCriado = alunoService.cadastrarNovoAluno(alunoDTO);
        return ResponseFactory.created(alunoCriado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<AlunoResponseDTO>> atualizarAluno(
            @PathVariable UUID id,
            @Valid @RequestBody AlunoUpdateDTO alunoUpdateDTO,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        authorizationService.checarDonoDoPerfil(userDetails, id);

        AlunoResponseDTO alunoAtualizado = alunoService.atualizarAluno(id, alunoUpdateDTO);
        return ResponseFactory.success(alunoAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirAluno(@PathVariable UUID id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        authorizationService.checarDonoDoPerfil(userDetails, id);

        alunoService.excluirAluno(id);
    }
}