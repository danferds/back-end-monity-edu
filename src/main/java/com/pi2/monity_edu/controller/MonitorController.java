package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.MonitorCadastroDTO;
import com.pi2.monity_edu.dto.MonitorResponseDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.service.MonitorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitores")
@RequiredArgsConstructor
public class MonitorController {

    private final MonitorService monitorService;

    @PostMapping("/cadastro")
    public ResponseEntity<ApiResponse<MonitorResponseDTO>> cadastrarMonitor(@Valid @RequestBody MonitorCadastroDTO monitorDTO) {
        MonitorResponseDTO monitorCriado = monitorService.cadastrarNovoMonitor(monitorDTO);
        return ResponseFactory.created(monitorCriado);
    }
}