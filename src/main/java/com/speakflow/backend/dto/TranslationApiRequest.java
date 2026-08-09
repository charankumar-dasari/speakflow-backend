package com.speakflow.backend.dto;

public class TranslationApiRequest {

    private String text;

    public TranslationApiRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}