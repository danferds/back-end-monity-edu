package com.pi2.monity_edu.mapper;

import com.pi2.monity_edu.dto.MonitorCadastroDTO;
import com.pi2.monity_edu.dto.MonitorResponseDTO;
import com.pi2.monity_edu.dto.MonitorUpdateDTO;
import com.pi2.monity_edu.model.Monitor;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MonitorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "status", ignore = true)
    Monitor toMonitor(MonitorCadastroDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "status", ignore = true)
    void popularMonitorDeDTO(MonitorCadastroDTO dto, @MappingTarget Monitor monitor);

    MonitorResponseDTO toMonitorResponseDTO(Monitor monitor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateMonitorFromDto(MonitorUpdateDTO dto, @MappingTarget Monitor monitor);
}