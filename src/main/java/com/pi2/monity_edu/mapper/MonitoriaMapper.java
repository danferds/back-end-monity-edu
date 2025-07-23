package com.pi2.monity_edu.mapper;

import com.pi2.monity_edu.dto.*;
import com.pi2.monity_edu.model.MaterialComplementar;
import com.pi2.monity_edu.model.Monitoria;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        imports = {MaterialComplementar.class, Collectors.class}
)
public interface MonitoriaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "monitor", ignore = true)
    @Mapping(target = "alunosInscritos", ignore = true)
    @Mapping(target = "materiais", ignore = true)
    @Mapping(target = "status", ignore = true)
    Monitoria toMonitoria(MonitoriaCadastroDTO dto);

    @Mapping(source = "monitor.id", target = "monitorId")
    @Mapping(source = "monitor.nome", target = "nomeMonitor")
    @Mapping(source = "materiais", target = "materiais")
    MonitoriaResponseDTO toMonitoriaResponseDTO(Monitoria monitoria);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nomeArquivo", target = "nomeArquivo")
    MaterialComplementarResponseDTO toMaterialComplementarResponseDTO(MaterialComplementar material);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "monitor", ignore = true)
    @Mapping(target = "alunosInscritos", ignore = true)
    @Mapping(target = "materiais", ignore = true)
    void updateMonitoriaFromDto(MonitoriaUpdateDTO dto, @MappingTarget Monitoria monitoria);

    @Mapping(source = "monitor.nome", target = "nomeMonitor")
    @Mapping(target = "avaliacaoMediaMonitor", constant = "0.0")
    @Mapping(source = "materiais", target = "materiais")
    AlunoMonitoriaResponseDTO toAlunoMonitoriaResponseDTO(Monitoria monitoria);

}