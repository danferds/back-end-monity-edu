package com.pi2.monity_edu.validation;

import com.pi2.monity_edu.dto.GerarCertificadoDTO;
import com.pi2.monity_edu.exception.CancelarMonitoriaException;
import com.pi2.monity_edu.model.Monitoria;
import com.pi2.monity_edu.model.StatusMonitoria;
import com.pi2.monity_edu.security.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

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
}