package com.pi2.monity_edu.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailExistenceService {

    private final RestTemplate restTemplate;

    @Value("${email.verify.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.zerobounce.net/v2/validate";

    public EmailExistenceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean verificarExistencia(String email) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("api_key", apiKey)
                .queryParam("email", email);

        try {
            Map<String, Object> response = restTemplate.getForObject(builder.toUriString(), Map.class);
            log.info("Resposta da API ZeroBounce para o e-mail {}: {}", email, response);

            if (response != null && response.containsKey("error")) {
                log.error("A API ZeroBounce retornou um erro: {}", response.get("error"));
                return false;
            }

            if (response != null && response.containsKey("status")) {
                String status = (String) response.get("status");
                return "valid".equalsIgnoreCase(status) || "catch-all".equalsIgnoreCase(status);
            }

            log.warn("A resposta da API ZeroBounce não pôde ser processada. Resposta: {}", response);
            return false;

        } catch (Exception e) {
            log.error("Falha na comunicação com a API ZeroBounce. Erro: {}", e.getMessage());
            return false;
        }
    }
}