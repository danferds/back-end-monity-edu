package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaResponseDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.security.AuthorizationService;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.service.MonitoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitorias")
@RequiredArgsConstructor
public class MonitoriaController {

    private final MonitoriaService monitoriaService;
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<ApiResponse<MonitoriaResponseDTO>> cadastrarMonitoria(
            @Valid @ModelAttribute MonitoriaCadastroDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MonitoriaResponseDTO monitoriaCriada = monitoriaService.cadastrarMonitoria(dto,
                userDetails.getUsuario().getId());
        return ResponseFactory.created(monitoriaCriada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MonitoriaResponseDTO>> getMonitoriaById(@PathVariable UUID id) {

        MonitoriaResponseDTO monitoria = monitoriaService.getMonitoriaById(id);
        return ResponseFactory.success(monitoria);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponse<String>> cancelarMonitoria(@PathVariable UUID id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        monitoriaService.cancelarMonitoria(id, userDetails);

        authorizationService.checarDonoDoPerfil(userDetails, id);

        return ResponseFactory.success("Monitoria cancelada com sucesso!");
    }
}