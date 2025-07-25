package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.GerarCertificadoDTO;
import com.pi2.monity_edu.dto.CertificadoFilterDTO;
import com.pi2.monity_edu.dto.CertificadoResponseDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.model.Certificado;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.service.CertificadoService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/certificados")
@RequiredArgsConstructor
public class CertificadoController {

    private final CertificadoService certificadoService;

    @PostMapping
    public ResponseEntity<ApiResponse<CertificadoResponseDTO>> gerarCertificado(
            @Valid @RequestBody GerarCertificadoDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        CertificadoResponseDTO certificadoCriado = certificadoService.gerarCertificado(dto, userDetails);

        return ResponseFactory.created(certificadoCriado);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CertificadoResponseDTO>>> listarCertificados(
            @ModelAttribute CertificadoFilterDTO filter,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<CertificadoResponseDTO> certificados = certificadoService.listarCertificados(filter, userDetails);

        return ResponseFactory.success(certificados);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadCertificado(@PathVariable UUID id) throws IOException {
        byte[] pdf = certificadoService.obterPdfCertificado(id);
        Certificado certificado = certificadoService.getCertificado(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + certificado.getNomeArquivo() + "\"")
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }
}