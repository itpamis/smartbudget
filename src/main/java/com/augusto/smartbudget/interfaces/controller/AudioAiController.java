package com.augusto.smartbudget.interfaces.controller;

import com.augusto.smartbudget.infrastructure.ai.AiAssistantService;
import com.augusto.smartbudget.infrastructure.ai.TtsClient;
import com.augusto.smartbudget.infrastructure.ai.WhisperClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ai")
public class AudioAiController {

    private final WhisperClient whisperClient;
    private final AiAssistantService aiAssistantService;
    private final TtsClient ttsClient;

    public AudioAiController(
            WhisperClient whisperClient,
            AiAssistantService aiAssistantService,
            TtsClient ttsClient) {

        this.whisperClient = whisperClient;
        this.aiAssistantService = aiAssistantService;
        this.ttsClient = ttsClient;
    }

    @PostMapping(
            value = "/audio",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = "audio/wav"
    )
    public ResponseEntity<byte[]> processAudio(
            @RequestParam("audio") MultipartFile audio) {

        try {

            // 1. Áudio → texto
            String transcription =
                    whisperClient.transcribe(audio);

            System.out.println(
                    "Transcrição: " + transcription
            );

            // 2. Texto → Llama + Tool Calling
            String response =
                    aiAssistantService.ask(transcription);

            System.out.println(
                    "Resposta da IA: " + response
            );

            // 3. Texto → áudio
            byte[] audioResponse =
                    ttsClient.synthesize(response);

            System.out.println(
                    "Áudio gerado: "
                            + audioResponse.length
                            + " bytes"
            );

            // 4. Retorna o WAV
            return ResponseEntity
                    .ok()
                    .contentType(
                            MediaType.parseMediaType(
                                    "audio/wav"
                            )
                    )
                    .body(audioResponse);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao processar áudio: "
                            + e.getMessage(),
                    e
            );
        }
    }

    @GetMapping(
            value = "/audio/test",
            produces = "audio/wav"
    )
    public byte[] testAudio() {

        try {

            return ttsClient.synthesize(
                    "Olá! Este é um teste de voz do Smart Budget."
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao gerar áudio: "
                            + e.getMessage(),
                    e
            );
        }
    }
}