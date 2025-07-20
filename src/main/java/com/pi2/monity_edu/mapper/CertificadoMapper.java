package com.pi2.monity_edu.mapper;

import com.pi2.monity_edu.dto.GerarCertificadoDTO;
import com.pi2.monity_edu.dto.CertificadoResponseDTO;
import com.pi2.monity_edu.model.Certificado;
import com.pi2.monity_edu.model.Monitoria;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = { Monitoria.class, Collectors.class })
public interface CertificadoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "monitoria", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "nomeArquivo", ignore = true)
    Certificado toCertificado(GerarCertificadoDTO dto);

    @Mapping(source = "monitoria.titulo", target = "tituloMonitoria")
    CertificadoResponseDTO toCerificadoResponseDTO(Certificado certificado);
}