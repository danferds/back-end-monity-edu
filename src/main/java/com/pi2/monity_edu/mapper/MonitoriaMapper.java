package com.pi2.monity_edu.mapper;

import com.pi2.monity_edu.dto.MaterialComplementarResponseDTO;
import com.pi2.monity_edu.dto.MonitoriaCadastroDTO;
import com.pi2.monity_edu.dto.MonitoriaResponseDTO;
import com.pi2.monity_edu.model.MaterialComplementar;
import com.pi2.monity_edu.model.Monitoria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
}