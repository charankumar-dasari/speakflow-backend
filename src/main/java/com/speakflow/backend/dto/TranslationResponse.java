package com.speakflow.backend.dto;

import java.time.LocalDateTime;

public class TranslationResponse {

    private Long id;
    private String detectedLanguage;
    private String originalText;
    private String englishText;
    private LocalDateTime createdAt;

    public TranslationResponse() {
    }

    public TranslationResponse(
            Long id,
            String detectedLanguage,
            String originalText,
            String englishText,
            LocalDateTime createdAt) {

        this.id = id;
        this.detectedLanguage = detectedLanguage;
        this.originalText = originalText;
        this.englishText = englishText;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getDetectedLanguage() {
        return detectedLanguage;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getEnglishText() {
        return englishText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}