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

        if (originalText == null || originalText.isBlank()) {
            throw new IllegalArgumentException(
                    "Speech text cannot be empty"
            );
        }

        String prompt = """
                You are SpeakFlow, a professional speech-to-English translation engine.

                Translate the user's speech into natural, clear English.

                Rules:
                1. Detect the input language automatically.
                2. The input can be Telugu, Hindi, Kannada, Tamil,
                   Malayalam, English, or mixed languages.
                3. Understand the complete meaning of the speech.
                4. Translate all non-English content into natural English.
                5. Keep existing English content natural.
                6. Correct obvious speech-recognition mistakes when the meaning is clear.
                7. Do not explain anything.
                8. Do not add quotation marks.
                9. Return ONLY the final English sentence.
                10. Preserve the original meaning.

                User speech:
                """ + originalText;

        try {

            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-2.5-flash",
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

        } catch (Exception e) {

            System.err.println(
                    "❌ Gemini translation error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "Gemini translation failed",
                    e
            );
        }
    }
}