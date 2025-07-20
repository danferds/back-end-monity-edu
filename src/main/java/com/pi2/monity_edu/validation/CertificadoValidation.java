package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.dto.GerarCertificadoDTO;
import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaUpdateDTO;
import com.pi2.monity_edu.exception.CancelarMonitoriaException;
import com.pi2.monity_edu.exception.HorarioInvalidoException;
import com.pi2.monity_edu.exception.MarcarMonitoriaComoRealizadaException;
import com.pi2.monity_edu.exception.MonitorNaoCredenciadoException;
import com.pi2.monity_edu.exception.MonitoriaNaoEditavelException;
import com.pi2.monity_edu.exception.TipoArquivoNaoSuportadoException;
import com.pi2.monity_edu.model.Monitor;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.model.StatusMonitor;
import com.pi2.monity_edu.model.StatusMonitoria;
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
public class CertificadoValidation {

    public void validarGerarCeritifcado(GerarCertificadoDTO dto, Monitoria monitoria, UserDetailsImpl userDetails) {
        if (!Objects.equals(monitoria.getMonitor().getId(), userDetails.getUsuario().getId())) {
            log.warn("Tentativa de gerar certificado por usuário não autorizado. User ID: {}, Monitoria ID: {}",
                    userDetails.getUsuario().getId(), monitoria.getId());
            throw new AccessDeniedException(
                    "Acesso negado. Você não tem permissão para gerar o certificado desta monitoria.");
        }

        if (monitoria.getStatus() != StatusMonitoria.REALIZADA) {
            log.warn("Tentativa de gerar um certificado de uma monitoria que não foi realizada. Monitoria ID: {}",
                    monitoria.getId());
            throw new CancelarMonitoriaException(
                    "Não é possível gerar o certificado de uma monitoria que ainda não foi realizada.");
        }
    }

    // public void validarEdicaoMonitoria(MonitoriaUpdateDTO dto, Monitoria
    // monitoria, UserDetailsImpl userDetails) {
    // podeEditar(monitoria, userDetails);

    // LocalTime horarioInicio = (dto.getHorarioInicio() != null) ?
    // dto.getHorarioInicio()
    // : monitoria.getHorarioInicio();
    // LocalTime horarioFim = (dto.getHorarioFim() != null) ? dto.getHorarioFim() :
    // monitoria.getHorarioFim();
    // validarHorarios(horarioInicio, horarioFim);

    // validarArquivos(dto.getNovosArquivos());
    // }

    // private void validarStatusMonitor(Monitor monitor) {
    // if (monitor.getStatus() != StatusMonitor.APROVADO) {
    // log.warn("Tentativa de cadastro de monitoria por monitor não credenciado. ID:
    // {}", monitor.getId());
    // throw new MonitorNaoCredenciadoException(
    // "Apenas monitores com status APROVADO podem cadastrar monitorias.");
    // }
    // }

    // private void validarHorarios(LocalTime horarioInicio, LocalTime horarioFim) {
    // if (horarioInicio != null && horarioFim != null &&
    // horarioInicio.isAfter(horarioFim)) {
    // throw new HorarioInvalidoException("O horário de fim deve ser posterior ao
    // horário de início.");
    // }
    // }

    // private void validarArquivos(List<MultipartFile> arquivos) {
    // if (arquivos == null || arquivos.isEmpty()) {
    // return;
    // }

    // for (MultipartFile arquivo : arquivos) {
    // if (arquivo == null || arquivo.isEmpty()) {
    // continue;
    // }

    // String contentType = arquivo.getContentType();
    // if (contentType == null || !TIPOS_ARQUIVO_SUPORTADOS.contains(contentType)) {
    // log.warn("Tentativa de upload de arquivo com tipo não suportado: {}",
    // contentType);
    // throw new TipoArquivoNaoSuportadoException("Tipo de arquivo não suportado: "
    // + arquivo.getOriginalFilename() + ". Utilize PDF, DOCX, PPTX, TXT, JPG,
    // PNG.");
    // }
    // }
    // }

    // public void podeCancelar(Monitoria monitoria, UserDetailsImpl userDetails) {
    // if (!Objects.equals(monitoria.getMonitor().getId(),
    // userDetails.getUsuario().getId())) {
    // log.warn("Tentativa de cancelamento por usuário não autorizado. User ID: {},
    // Monitoria ID: {}",
    // userDetails.getUsuario().getId(), monitoria.getId());
    // throw new AccessDeniedException("Acesso negado. Você não tem permissão para
    // cancelar esta monitoria.");
    // }

    // if (monitoria.getStatus() == StatusMonitoria.CANCELADA) {
    // log.warn("Tentativa de cancelar uma monitoria que já está cancelada.
    // Monitoria ID: {}", monitoria.getId());
    // throw new CancelarMonitoriaException("A monitoria já foi cancelada.");
    // }

    // if (monitoria.getStatus() == StatusMonitoria.REALIZADA) {
    // log.warn("Tentativa de cancelar uma monitoria que já foi realizada. Monitoria
    // ID: {}", monitoria.getId());
    // throw new CancelarMonitoriaException("Não é possível cancelar uma monitoria
    // que já foi realizada.");
    // }
    // }

    // public void podeMarcarComoRealizada(Monitoria monitoria, UserDetailsImpl
    // userDetails) {
    // if (!Objects.equals(monitoria.getMonitor().getId(),
    // userDetails.getUsuario().getId())) {
    // log.warn("Tentativa por usuário não autorizado. User ID: {}, Monitoria ID:
    // {}",
    // userDetails.getUsuario().getId(), monitoria.getId());
    // throw new AccessDeniedException(
    // "Acesso negado. Você não tem permissão para marcar esta monitoria como
    // realizada.");
    // }

    // if (monitoria.getStatus() == StatusMonitoria.CANCELADA) {
    // log.warn("Tentativa de marcar como realizada uma monitoria que já está
    // cancelada. Monitoria ID: {}",
    // monitoria.getId());
    // throw new MarcarMonitoriaComoRealizadaException("A monitoria já foi
    // cancelada.");
    // }

    // if (monitoria.getStatus() == StatusMonitoria.REALIZADA) {
    // log.warn("Tentativa de marcar como realizada uma monitoria que já foi
    // realizada. Monitoria ID: {}",
    // monitoria.getId());
    // throw new MarcarMonitoriaComoRealizadaException(
    // "Não é possível cancelar uma monitoria que já foi realizada.");
    // }
    // }

    // public void podeEditar(Monitoria monitoria, UserDetailsImpl userDetails) {
    // if (!Objects.equals(monitoria.getMonitor().getId(),
    // userDetails.getUsuario().getId())) {
    // log.warn("Tentativa de edição por usuário não autorizado. User ID: {},
    // Monitoria ID: {}",
    // userDetails.getUsuario().getId(), monitoria.getId());
    // throw new AccessDeniedException("Acesso negado. Você não tem permissão para
    // editar esta monitoria.");
    // }

    // if (monitoria.getStatus() == StatusMonitoria.CANCELADA) {
    // log.warn("Tentativa de cancelar uma monitoria que já está cancelada.
    // Monitoria ID: {}", monitoria.getId());
    // throw new MonitoriaNaoEditavelException("Não é possível editar monitoria que
    // foi cancelada.");
    // }

    // if (monitoria.getStatus() == StatusMonitoria.REALIZADA) {
    // log.warn("Tentativa de cancelar uma monitoria que já foi realizada. Monitoria
    // ID: {}", monitoria.getId());
    // throw new MonitoriaNaoEditavelException("Não é possível editar uma monitoria
    // que já foi realizada.");
    // }
    // }
}