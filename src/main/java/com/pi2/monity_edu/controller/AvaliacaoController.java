package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.AvaliacaoDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.service.AvaliacaoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/monitorias/{monitoriaId}/avaliar")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> avaliarMonitor(
            @PathVariable UUID monitoriaId,
            @Valid @RequestBody AvaliacaoDTO avaliacaoDTO,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        avaliacaoService.avaliarMonitoria(monitoriaId, userDetails, avaliacaoDTO);
        return ResponseFactory.success("Avaliação registrada com sucesso!");
    }
}