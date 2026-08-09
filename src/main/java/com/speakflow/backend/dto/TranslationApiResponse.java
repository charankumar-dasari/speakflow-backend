package com.speakflow.backend.dto;

public class TranslationApiResponse {

    private String originalText;
    private String englishText;

    public TranslationApiResponse() {
    }

    public TranslationApiResponse(
            String originalText,
            String englishText) {

        this.originalText = originalText;
        this.englishText = englishText;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getEnglishText() {
        return englishText;
    }
}