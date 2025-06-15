package com.pi2.monity_edu.mapper;

import com.pi2.monity_edu.dto.AlunoCadastroDTO;
import com.pi2.monity_edu.dto.AlunoResponseDTO;
import com.pi2.monity_edu.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    Aluno toAluno(AlunoCadastroDTO dto);

    /**
     * Popula uma instância de Aluno existente com os dados de um AlunoCadastroDTO.
     * As propriedades 'id' e 'senha' são ignoradas durante o mapeamento.
     *
     * @param dto O DTO com os dados de origem.
     * @param aluno A instância de Aluno a ser populada.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    void popularAlunoDeDTO(AlunoCadastroDTO dto, @MappingTarget Aluno aluno);

    AlunoResponseDTO toAlunoResponseDTO(Aluno aluno);
}