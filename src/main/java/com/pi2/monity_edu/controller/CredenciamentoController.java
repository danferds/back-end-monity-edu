package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.CredenciamentoCadastroDTO;
import com.pi2.monity_edu.dto.CredenciamentoResponseDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.service.CredenciamentoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credenciar")
@RequiredArgsConstructor
public class CredenciamentoController {

    private final CredenciamentoService credenciamentoService;

    @PostMapping("/monitor")
    public ResponseEntity<ApiResponse<CredenciamentoResponseDTO>> credenciamentoMonitor(
            @Valid @RequestBody CredenciamentoCadastroDTO dto) {
        CredenciamentoResponseDTO credenciamento = credenciamentoService.processarCredenciamento(dto);

        return ResponseFactory.created(credenciamento);
    }
}