package com.AI_Posts.Service.AI;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiService {
    private final String GEMINI_KEY = "GEMINI_KEY";
    private final String GEMINI_URL = "GEMINI_URL";

    private final RestTemplate restTemplate = new RestTemplate();

    // só pra testes depois excluir essa fdunção
    public String generateContent() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-goog-api-key", GEMINI_KEY);

        String body = "{\"contents\":[{\"role\":\"user\",\"parts\":[{\"text\":\"Give me five subcategories of punk?\"}]}]}";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(GEMINI_URL, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    public String validadeAI(String userInput) {

        String prompt = "Sempre retorne apenas um dígito. Analise se a seguinte mensagem é ofensiva, caso seja, retorne 0, caso contrário retorne 1. " +
                "Mensagem: " + userInput;
        System.out.println(prompt);
        String jsonPayload = String.format("{\"contents\":[{\"role\": \"user\",\"parts\":[{\"text\": \"%s\"}]}]}", prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-goog-api-key", GEMINI_KEY);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<String> response = restTemplate.exchange(GEMINI_URL, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return extractResponseFromJson(response.getBody());
        } else {
            throw new RuntimeException("Failed to call API: " + response.getStatusCode());
        }
    }

    private String extractResponseFromJson(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode textNode = rootNode.path("candidates").get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text");

            return textNode.asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse API response", e);
        }
    }
}
