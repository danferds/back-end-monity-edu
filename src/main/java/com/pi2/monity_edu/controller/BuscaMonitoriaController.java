package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.AlunoMonitoriaResponseDTO;
import com.pi2.monity_edu.dto.BuscaMonitoriaFilterDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.service.BuscaMonitoriaService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buscar-monitorias")
@RequiredArgsConstructor
public class BuscaMonitoriaController {

    private final BuscaMonitoriaService buscaMonitoriaService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<AlunoMonitoriaResponseDTO>>> buscarMonitoriasDisponiveis(
            @ModelAttribute BuscaMonitoriaFilterDTO filter) {
        List<AlunoMonitoriaResponseDTO> monitorias = buscaMonitoriaService.buscarMonitoriasDisponiveis(filter);
        return ResponseFactory.success(monitorias);
    }
}