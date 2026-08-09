package com.speakflow.backend.dto;

public class TranslationRequest {

    private String detectedLanguage;
    private String originalText;

    public TranslationRequest() {
    }

    public String getDetectedLanguage() {
        return detectedLanguage;
    }

    public void setDetectedLanguage(String detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }
}