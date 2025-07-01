package com.pi2.monity_edu.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.Map;

@Service
public class DomainValidationService {

    private final RestTemplate restTemplate;

    @Value("${apyhub.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.apyhub.com/validate/email/academic";

    public DomainValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isAcademicEmail(String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("apy-token", apiKey);

        Map<String, String> requestBody = Collections.singletonMap("email", email);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            Map<String, Boolean> response = restTemplate.postForObject(API_URL, entity, Map.class);
            return response != null && response.getOrDefault("data", false);
        } catch (Exception e) {
            return false;
        }
    }
}