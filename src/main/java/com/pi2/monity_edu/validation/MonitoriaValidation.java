package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaUpdateDTO;
import com.pi2.monity_edu.exception.*;
import com.pi2.monity_edu.model.*;
import com.pi2.monity_edu.security.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class MonitoriaValidation {

    private static final List<String> TIPOS_ARQUIVO_SUPORTADOS = Arrays.asList(
            "application/pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation", "text/plain",
            "image/jpeg", "image/png");

    public void validarCadastroMonitoria(MonitoriaCadastroDTO dto, Monitor monitor) {
        validarStatusMonitor(monitor);
        validarHorarios(dto.getHorarioInicio(), dto.getHorarioFim());
        validarArquivos(dto.getArquivos());
    }

    public void validarEdicaoMonitoria(MonitoriaUpdateDTO dto, Monitoria monitoria, UserDetailsImpl userDetails) {
        podeEditar(monitoria, userDetails);

        LocalTime horarioInicio = (dto.getHorarioInicio() != null) ? dto.getHorarioInicio()
                : monitoria.getHorarioInicio();
        LocalTime horarioFim = (dto.getHorarioFim() != null) ? dto.getHorarioFim() : monitoria.getHorarioFim();
        validarHorarios(horarioInicio, horarioFim);

        validarArquivos(dto.getNovosArquivos());
    }

    private void validarStatusMonitor(Monitor monitor) {
        if (monitor.getStatus() != StatusMonitor.APROVADO) {
            log.warn("Tentativa de cadastro de monitoria por monitor não credenciado. ID: {}", monitor.getId());
            throw new MonitorNaoCredenciadoException(
                    "Apenas monitores com status APROVADO podem cadastrar monitorias.");
        }
    }

    private void validarHorarios(LocalTime horarioInicio, LocalTime horarioFim) {
        if (horarioInicio != null && horarioFim != null && horarioInicio.isAfter(horarioFim)) {
            throw new HorarioInvalidoException("O horário de fim deve ser posterior ao horário de início.");
        }
    }

    private void validarArquivos(List<MultipartFile> arquivos) {
        if (arquivos == null || arquivos.isEmpty()) {
            return;
        }

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
        if (!Objects.equals(monitoria.getMonitor().getId(), userDetails.getUsuario().getId())) {
            log.warn("Tentativa de cancelamento por usuário não autorizado. User ID: {}, Monitoria ID: {}",
                    userDetails.getUsuario().getId(), monitoria.getId());
            throw new AccessDeniedException("Acesso negado. Você não tem permissão para cancelar esta monitoria.");
        }

        if (monitoria.getStatus() == StatusMonitoria.CANCELADA) {
            log.warn("Tentativa de cancelar uma monitoria que já está cancelada. Monitoria ID: {}", monitoria.getId());
            throw new CancelarMonitoriaException("A monitoria já foi cancelada.");
        }

        if (monitoria.getStatus() == StatusMonitoria.REALIZADA) {
            log.warn("Tentativa de cancelar uma monitoria que já foi realizada. Monitoria ID: {}", monitoria.getId());
            throw new CancelarMonitoriaException("Não é possível cancelar uma monitoria que já foi realizada.");
        }
    }

    public void podeMarcarComoRealizada(Monitoria monitoria, UserDetailsImpl userDetails) {
        if (!Objects.equals(monitoria.getMonitor().getId(), userDetails.getUsuario().getId())) {
            log.warn("Tentativa por usuário não autorizado. User ID: {}, Monitoria ID: {}",
                    userDetails.getUsuario().getId(), monitoria.getId());
            throw new AccessDeniedException(
                    "Acesso negado. Você não tem permissão para marcar esta monitoria como realizada.");
        }

        if (monitoria.getStatus() == StatusMonitoria.CANCELADA) {
            log.warn("Tentativa de marcar como realizada uma monitoria que já está cancelada. Monitoria ID: {}",
                    monitoria.getId());
            throw new MarcarMonitoriaComoRealizadaException("A monitoria já foi cancelada.");
        }

        if (monitoria.getStatus() == StatusMonitoria.REALIZADA) {
            log.warn("Tentativa de marcar como realizada uma monitoria que já foi realizada. Monitoria ID: {}",
                    monitoria.getId());
            throw new MarcarMonitoriaComoRealizadaException(
                    "Não é possível cancelar uma monitoria que já foi realizada.");
        }
    }

    public void podeEditar(Monitoria monitoria, UserDetailsImpl userDetails) {
        if (!Objects.equals(monitoria.getMonitor().getId(), userDetails.getUsuario().getId())) {
            log.warn("Tentativa de edição por usuário não autorizado. User ID: {}, Monitoria ID: {}",
                    userDetails.getUsuario().getId(), monitoria.getId());
            throw new AccessDeniedException("Acesso negado. Você não tem permissão para editar esta monitoria.");
        }

        if (monitoria.getStatus() == StatusMonitoria.CANCELADA) {
            log.warn("Tentativa de cancelar uma monitoria que já está cancelada. Monitoria ID: {}", monitoria.getId());
            throw new MonitoriaNaoEditavelException("Não é possível editar monitoria que foi cancelada.");
        }

        if (monitoria.getStatus() == StatusMonitoria.REALIZADA) {
            log.warn("Tentativa de cancelar uma monitoria que já foi realizada. Monitoria ID: {}", monitoria.getId());
            throw new MonitoriaNaoEditavelException("Não é possível editar uma monitoria que já foi realizada.");
        }
    }

    public void validarInscricao(Monitoria monitoria, Aluno aluno) {
        if (monitoria.getStatus() != StatusMonitoria.PENDENTE) {
            log.warn("Tentativa de inscrição na monitoria ID {} com status {}", monitoria.getId(), monitoria.getStatus());
            throw new InscricaoMonitoriaException("Não é possível se inscrever em uma monitoria que não está pendente.");
        }

        if (monitoria.getAlunosInscritos().contains(aluno)) {
            log.warn("Aluno ID {} já está inscrito na monitoria ID {}", aluno.getId(), monitoria.getId());
            throw new InscricaoMonitoriaException("Você já está inscrito nesta monitoria.");
        }

        if (monitoria.getAlunosInscritos().size() >= 50) {
            log.warn("Monitoria ID {} atingiu o número máximo de inscritos.", monitoria.getId());
            throw new InscricaoMonitoriaException("Não há mais vagas disponíveis para está monitoria.");
        }
    }

    public void validarCancelamentoInscricao(Monitoria monitoria, Aluno aluno) {
        if (monitoria.getStatus() != StatusMonitoria.PENDENTE) {
            log.warn("Tentativa de cancelar inscrição em monitoria com status {}", monitoria.getStatus());
            throw new InscricaoMonitoriaException("Não é possível cancelar a inscrição de uma monitoria que não está pendente.");
        }

        if (!monitoria.getAlunosInscritos().contains(aluno)) {
            log.warn("Tentativa de cancelamento de inscrição do aluno ID {} que não está inscrito na monitoria ID {}", aluno.getId(), monitoria.getId());
            throw new InscricaoMonitoriaException("Você não está inscrito nesta monitoria para cancelar a inscrição.");
        }
    }
}