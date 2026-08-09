package com.speakflow.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "speech_translations")
public class SpeechTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String detectedLanguage;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String originalText;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String englishText;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public SpeechTranslation() {
    }

    public SpeechTranslation(
            String detectedLanguage,
            String originalText,
            String englishText,
            LocalDateTime createdAt) {

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

    public void setDetectedLanguage(String detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}