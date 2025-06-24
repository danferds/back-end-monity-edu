package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.MonitorCadastroDTO;
import com.pi2.monity_edu.dto.MonitorResponseDTO;
import com.pi2.monity_edu.dto.MonitorUpdateDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.security.AuthorizationService;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.service.MonitorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/monitores")
@RequiredArgsConstructor
public class MonitorController {

    private final MonitorService monitorService;
    private final AuthorizationService authorizationService;

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<MonitorResponseDTO>> cadastrarMonitor(@Valid @RequestBody MonitorCadastroDTO monitorDTO) {
        MonitorResponseDTO monitorCriado = monitorService.cadastrarNovoMonitor(monitorDTO);
        return ResponseFactory.created(monitorCriado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<MonitorResponseDTO>> atualizarMonitor(
            @PathVariable UUID id,
            @Valid @RequestBody MonitorUpdateDTO monitorUpdateDTO,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        authorizationService.checarDonoDoPerfil(userDetails, id);

        MonitorResponseDTO monitorAtualizado = monitorService.atualizarMonitor(id, monitorUpdateDTO);
        return ResponseFactory.success(monitorAtualizado);
    }
}