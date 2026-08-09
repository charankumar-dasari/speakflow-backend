package com.speakflow.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";

    public String translateToEnglish(String originalText) {

        String prompt = """
                You are a professional translation engine.

                Translate the following speech into natural English.

                IMPORTANT RULES:
                1. Detect the language automatically.
                2. The input may contain Telugu, Hindi, Kannada,
                   Tamil, English, or mixed languages.
                3. Translate ALL non-English words into English.
                4. Keep existing English words natural.
                5. Do not explain the translation.
                6. Return ONLY the final English sentence.
                7. Preserve the original meaning.

                Speech:
                """ + originalText;

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of("text", prompt)
                                }
                        )
                }
        );

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", apiKey);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(
                        GEMINI_URL,
                        HttpMethod.POST,
                        request,
                        Map.class
                );

        Map body = response.getBody();

        if (body == null) {
            throw new RuntimeException("Empty response from Gemini");
        }

        var candidates = (java.util.List<Map>) body.get("candidates");

        if (candidates == null || candidates.isEmpty()) {
            throw new RuntimeException("No translation received from Gemini");
        }

        Map candidate = candidates.get(0);

        Map content = (Map) candidate.get("content");

        var parts = (java.util.List<Map>) content.get("parts");

        return (String) parts.get(0).get("text");
    }
}