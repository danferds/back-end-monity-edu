package com.pi2.monity_edu.service;

import com.pi2.monity_edu.dto.MonitoriaUpdateDTO;
import com.pi2.monity_edu.model.MaterialComplementar;
import com.pi2.monity_edu.model.Monitoria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MonitoriaMaterialService {

    private final MaterialComplementarService materialComplementarService;

    public void atualizarMateriaisDaMonitoria(MonitoriaUpdateDTO dto, Monitoria monitoria) {

        if (dto.getMateriaisParaRemover() != null && !dto.getMateriaisParaRemover().isEmpty()) {
            log.info("Removendo {} materiais da monitoria ID: {}", dto.getMateriaisParaRemover().size(), monitoria.getId());
            monitoria.getMateriais()
                    .removeIf(material -> dto.getMateriaisParaRemover().contains(material.getId()));
        }


        if (dto.getNovosArquivos() != null && !dto.getNovosArquivos().isEmpty()) {
            log.info("Adicionando {} novos materiais na monitoria ID: {}", dto.getNovosArquivos().size(), monitoria.getId());
            processarNovosMateriais(dto.getNovosArquivos(), monitoria);
        }
    }

    public void processarNovosMateriais(List<MultipartFile> arquivos, Monitoria monitoria) {
        if (arquivos != null && !arquivos.isEmpty()) {
            List<MaterialComplementar> novosMateriais = arquivos.stream()
                    .filter(arquivo -> arquivo != null && !arquivo.isEmpty())
                    .map(arquivo -> materialComplementarService.criarMaterial(arquivo, monitoria))
                    .collect(Collectors.toList());
            monitoria.getMateriais().addAll(novosMateriais);
        }
    }
}