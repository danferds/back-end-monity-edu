package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaFilterDTO;
import com.pi2.monity_edu.dto.MonitoriaResponseDTO;
import com.pi2.monity_edu.dto.MonitoriaUpdateDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.service.MonitoriaService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/monitorias")
@RequiredArgsConstructor
public class MonitoriaController {

    private final MonitoriaService monitoriaService;

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

        return ResponseFactory.success("Monitoria cancelada com sucesso!");
    }

    @PatchMapping("/{id}/realizada")
    public ResponseEntity<ApiResponse<String>> marcarMonitoriaComoRealizada(@PathVariable UUID id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        monitoriaService.marcarMonitoriaComoRealizada(id, userDetails);

        return ResponseFactory.success("Sucesso ao marcar monitoria como realizada!");
    }

    @PatchMapping("/{id}/editar")
    public ResponseEntity<ApiResponse<MonitoriaResponseDTO>> atualizarMonitoria(
            @PathVariable UUID id,
            @Valid @ModelAttribute MonitoriaUpdateDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MonitoriaResponseDTO monitoriaAtualizada = monitoriaService.atualizarMonitoria(id, dto, userDetails);

        return ResponseFactory.success(monitoriaAtualizada);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MonitoriaResponseDTO>>> listarMonitorias(
            @ModelAttribute MonitoriaFilterDTO filter,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<MonitoriaResponseDTO> monitorias = monitoriaService.listarMonitorias(filter, userDetails);

        return ResponseFactory.success(monitorias);
    }
}