package com.speakflow.backend.controller;

import com.speakflow.backend.dto.TranslationRequest;
import com.speakflow.backend.dto.TranslationResponse;
import com.speakflow.backend.entity.SpeechTranslation;
import com.speakflow.backend.service.SpeechTranslationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speech")
@CrossOrigin(origins = "http://localhost:5173")
public class SpeechTranslationController {

    private final SpeechTranslationService service;

    public SpeechTranslationController(
            SpeechTranslationService service) {

        this.service = service;
    }

    @PostMapping("/translate")
    public TranslationResponse translate(
            @RequestBody TranslationRequest request) {

        return service.saveTranslation(request);
    }

    @GetMapping("/history")
    public List<SpeechTranslation> getHistory() {

        return service.getAllTranslations();
    }

    @DeleteMapping("/history/{id}")
    public ResponseEntity<Void> deleteTranslation(
            @PathVariable Long id) {

        service.deleteTranslation(id);

        return ResponseEntity.noContent().build();
    }
}