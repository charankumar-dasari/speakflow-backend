package com.speakflow.backend.repository;

import com.speakflow.backend.entity.SpeechTranslation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeechTranslationRepository
        extends JpaRepository<SpeechTranslation, Long> {
}