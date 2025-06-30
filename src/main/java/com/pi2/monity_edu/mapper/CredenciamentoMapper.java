package com.pi2.monity_edu.mapper;

import com.pi2.monity_edu.dto.CredenciamentoCadastroDTO;
import com.pi2.monity_edu.dto.CredenciamentoResponseDTO;
import com.pi2.monity_edu.model.Credenciamento;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CredenciamentoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    Credenciamento toValidacao(CredenciamentoCadastroDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    void popularValidacaoDeDTO(CredenciamentoCadastroDTO dto, @MappingTarget Credenciamento validacao);

    CredenciamentoResponseDTO toValidacaoResponseDTO(Credenciamento validacao);
}