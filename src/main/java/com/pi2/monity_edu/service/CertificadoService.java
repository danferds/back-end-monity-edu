package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.GerarCertificadoDTO;
import com.pi2.monity_edu.dto.CertificadoFilterDTO;
import com.pi2.monity_edu.dto.CertificadoResponseDTO;
import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaFilterDTO;
import com.pi2.monity_edu.dto.MonitoriaResponseDTO;
import com.pi2.monity_edu.dto.MonitoriaUpdateDTO;
import com.pi2.monity_edu.finder.FinderMonitoria;
import com.pi2.monity_edu.finder.MonitorFinder;
import com.pi2.monity_edu.mapper.CertificadoMapper;
import com.pi2.monity_edu.mapper.MonitoriaMapper;
import com.pi2.monity_edu.model.Certificado;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.model.StatusMonitoria;
import com.pi2.monity_edu.repository.CertificadoRepository;
import com.pi2.monity_edu.repository.MonitoriaRepository;
import com.pi2.monity_edu.repository.specification.CertificadoSpecification;
import com.pi2.monity_edu.repository.specification.MonitoriaSpecification;
import com.pi2.monity_edu.security.UserDetailsImpl;
import com.pi2.monity_edu.validation.CertificadoValidation;
import com.pi2.monity_edu.validation.MonitoriaValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;
    private final CertificadoMapper certificadoMapper;
    private final CertificadoValidation certificadoValidation;
    private final FinderMonitoria monitoriaFinder;
    private final CertificadoPdfService certificadoPdfService;
    private final CertificadoSpecification certificadoSpecification;

    @Transactional
    public CertificadoResponseDTO gerarCertificado(GerarCertificadoDTO dto, UserDetailsImpl userDetails)
            throws IOException {
        log.info("Iniciando processo de gerar certificado para o monitor ID: {}", userDetails.getUsername());

        Monitoria monitoria = monitoriaFinder.buscarPorId(dto.getMonitoriaId());
        certificadoValidation.validarGerarCeritifcado(dto, monitoria, userDetails);

        Certificado novoCertificado = certificadoMapper.toCertificado(dto);
        novoCertificado.setMonitoria(monitoria);
        novoCertificado.setDataCriacao(LocalDateTime.now());

        byte[] pdf = certificadoPdfService.gerarPdf(novoCertificado);

        certificadoPdfService.salvarPdf(novoCertificado, pdf);

        Certificado certificadoSalvo = certificadoRepository.save(novoCertificado);

        log.info("Monitoria cadastrada com sucesso. ID: {}", certificadoSalvo.getId());

        return certificadoMapper.toCerificadoResponseDTO(certificadoSalvo);
    }

    @Transactional(readOnly = true)
    public List<CertificadoResponseDTO> listarCertificados(CertificadoFilterDTO filter,
            UserDetailsImpl userDetails) {

        UUID monitorId = userDetails.getUsuario().getId();

        log.info("Buscando certificados para o monitor ID: {} com os filtros: {}",
                monitorId, filter);

        Specification<Certificado> specificacao = certificadoSpecification.getCertificados(filter, monitorId);
        List<Certificado> certificados = certificadoRepository.findAll(specificacao,
                Sort.by("dataCriacao").descending());

        return certificados.stream()
                .map(certificadoMapper::toCerificadoResponseDTO)
                .collect(Collectors.toList());
    }

    public byte[] obterPdfCertificado(UUID id) throws IOException {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado não encontrado"));
        byte[] certificadoFile = Files.readAllBytes(Paths.get("uploads/certificados/" + certificado.getNomeArquivo()));
        log.info(certificadoFile.length > 0 ? "Certificado encontrado e pronto para download."
                : "Certificado não encontrado ou vazio.");
        return certificadoFile;
    }

    public Certificado getCertificado(UUID id) {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado não encontrado"));
        return certificado;
    }
}