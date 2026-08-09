package com.speakflow.backend.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiTranslationService {

    private final Client client;

    public GeminiTranslationService() {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "GEMINI_API_KEY environment variable is not set"
            );
        }

        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String translateToEnglish(String originalText) {

        String prompt = """
                You are SpeakFlow, a speech-to-English translation engine.

                Convert the user's speech into natural, clear English.

                Rules:
                1. Detect the language automatically.
                2. The input may contain Telugu, Hindi, Kannada,
                   Tamil, Malayalam, English, or mixed languages.
                3. If the input is mixed-language speech,
                   understand the complete meaning and convert it
                   into natural English.
                4. Do not explain the translation.
                5. Do not add quotation marks.
                6. Return ONLY the final English sentence.
                7. Preserve the original meaning.
                8. Correct obvious speech-recognition mistakes
                   when the intended meaning is clear.

                User speech:
                """ + originalText;

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3.6-flash",
                        prompt,
                        null
                );

        String result = response.text();

        if (result == null || result.isBlank()) {
            throw new RuntimeException(
                    "Gemini returned an empty translation"
            );
        }

        return result.trim();
    }
}