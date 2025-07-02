package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaResponseDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.service.MonitoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitorias")
@RequiredArgsConstructor
public class MonitoriaController {

    private final MonitoriaService monitoriaService;

    @PostMapping
    public ResponseEntity<ApiResponse<MonitoriaResponseDTO>> cadastrarMonitoria(
            @Valid @ModelAttribute MonitoriaCadastroDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MonitoriaResponseDTO monitoriaCriada = monitoriaService.cadastrarMonitoria(dto, userDetails.getUsuario().getId());
        return ResponseFactory.created(monitoriaCriada);
    }
}