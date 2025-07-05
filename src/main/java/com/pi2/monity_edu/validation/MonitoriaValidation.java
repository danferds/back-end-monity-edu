package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.exception.CancelarMonitoriaException;
import com.pi2.monity_edu.exception.HorarioInvalidoException;
import com.pi2.monity_edu.exception.MonitorNaoCredenciadoException;
import com.pi2.monity_edu.exception.TipoArquivoNaoSuportadoException;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.model.StatusMonitor;
import com.pi2.monity_edu.model.StatusMonitoria;
import com.pi2.monity_edu.security.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class MonitoriaValidation {

    private static final List<String> TIPOS_ARQUIVO_SUPORTADOS = Arrays.asList(
            "application/pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation", "text/plain",
            "image/jpeg", "image/png");

    public void validarCadastroMonitoria(MonitoriaCadastroDTO dto, Monitor monitor) {
        validarStatusMonitor(monitor);
        validarHorarios(dto);
        if (dto.getArquivos() != null && !dto.getArquivos().isEmpty()) {
            if (dto.getArquivos().stream().anyMatch(arquivo -> !arquivo.isEmpty())) {
                validarArquivos(dto.getArquivos());
            }
        }
    }

    private void validarStatusMonitor(Monitor monitor) {
        if (monitor.getStatus() != StatusMonitor.APROVADO) {
            log.warn("Tentativa de cadastro de monitoria por monitor não credenciado. ID: {}", monitor.getId());
            throw new MonitorNaoCredenciadoException(
                    "Apenas monitores com status APROVADO podem cadastrar monitorias.");
        }
    }

    private void validarHorarios(MonitoriaCadastroDTO dto) {
        if (dto.getHorarioInicio().isAfter(dto.getHorarioFim())) {
            throw new HorarioInvalidoException("O horário de fim deve ser posterior ao horário de início.");
        }
    }

    private void validarArquivos(List<MultipartFile> arquivos) {
        for (MultipartFile arquivo : arquivos) {
            if (arquivo == null || arquivo.isEmpty()) {
                continue;
            }
            String contentType = arquivo.getContentType();
            if (contentType == null || !TIPOS_ARQUIVO_SUPORTADOS.contains(contentType)) {
                log.warn("Tentativa de upload de arquivo com tipo não suportado: {}", contentType);
                throw new TipoArquivoNaoSuportadoException("Tipo de arquivo não suportado: "
                        + arquivo.getOriginalFilename() + ". Utilize PDF, DOCX, PPTX, TXT, JPG, PNG.");
            }
        }
    }

    public void podeCancelar(Monitoria monitoria, UserDetailsImpl userDetails) {
        if (monitoria.getStatus() == StatusMonitoria.REALIZADA) {
            log.warn("Não é possível cancelar uma monitoria que já foi realizada");
            throw new CancelarMonitoriaException("Não é possível cancelar uma monitoria que já foi realizada");
        }

        if (monitoria.getStatus() == StatusMonitoria.CANCELADA) {
            log.warn("A monitoria já está cancelada");
            throw new CancelarMonitoriaException("A monitoria já está cancelada");
        }
    }
}