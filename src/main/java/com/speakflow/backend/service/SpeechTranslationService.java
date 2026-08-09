package com.speakflow.backend.service;

import com.speakflow.backend.dto.TranslationRequest;
import com.speakflow.backend.dto.TranslationResponse;
import com.speakflow.backend.entity.SpeechTranslation;
import com.speakflow.backend.repository.SpeechTranslationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpeechTranslationService {

	private final SpeechTranslationRepository repository;
	private final GeminiTranslationService geminiService;

	public SpeechTranslationService(
	        SpeechTranslationRepository repository,
	        GeminiTranslationService geminiService) {

	    this.repository = repository;
	    this.geminiService = geminiService;
	}
	public TranslationResponse saveTranslation(
	        TranslationRequest request) {

	    String englishText =
	            geminiService.translateToEnglish(
	                    request.getOriginalText()
	            );

	    SpeechTranslation translation =
	            new SpeechTranslation();

	    translation.setDetectedLanguage(
	            request.getDetectedLanguage()
	    );

	    translation.setOriginalText(
	            request.getOriginalText()
	    );

	    translation.setEnglishText(
	            englishText
	    );

	    translation.setCreatedAt(
	            LocalDateTime.now()
	    );

	    SpeechTranslation saved =
	            repository.save(translation);

	    return new TranslationResponse(
	            saved.getId(),
	            saved.getDetectedLanguage(),
	            saved.getOriginalText(),
	            saved.getEnglishText(),
	            saved.getCreatedAt()
	    );
	}

    public List<SpeechTranslation> getAllTranslations() {
        return repository.findAll();
    }
    public void deleteTranslation(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Translation not found with id: " + id
            );
        }

        repository.deleteById(id);
    }
}