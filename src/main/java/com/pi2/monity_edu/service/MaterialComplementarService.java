package com.pi2.monity_edu.service;

import com.pi2.monity_edu.model.MaterialComplementar;
import com.pi2.monity_edu.model.Monitoria;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class MaterialComplementarService {

    public MaterialComplementar criarMaterial(MultipartFile arquivo, Monitoria monitoria) {
        try {
            MaterialComplementar material = new MaterialComplementar();
            material.setNomeArquivo(arquivo.getOriginalFilename());
            material.setTipoArquivo(arquivo.getContentType());
            material.setDados(arquivo.getBytes());
            material.setMonitoria(monitoria);
            return material;
        } catch (IOException e) {
            log.error("Erro ao processar o arquivo: {}", arquivo.getOriginalFilename(), e);
            throw new RuntimeException("Erro ao processar o upload do arquivo.");
        }
    }
}