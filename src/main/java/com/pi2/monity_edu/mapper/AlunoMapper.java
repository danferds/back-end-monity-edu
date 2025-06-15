package com.pi2.monity_edu.mapper;

import com.pi2.monity_edu.dto.AlunoCadastroDTO;
import com.pi2.monity_edu.dto.AlunoResponseDTO;
import com.pi2.monity_edu.dto.AlunoUpdateDTO;
import com.pi2.monity_edu.model.Aluno;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    Aluno toAluno(AlunoCadastroDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    void popularAlunoDeDTO(AlunoCadastroDTO dto, @MappingTarget Aluno aluno);

    AlunoResponseDTO toAlunoResponseDTO(Aluno aluno);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    void updateAlunoFromDto(AlunoUpdateDTO dto, @MappingTarget Aluno aluno);
}