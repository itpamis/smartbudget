package com.augusto.smartbudget.infrastructure.ai;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WhisperClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String transcribe(MultipartFile audio)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5000/transcribe"))
                .header("Content-Type", "audio/wav")
                .POST(
                        HttpRequest.BodyPublishers.ofByteArray(
                                audio.getBytes()
                        )
                )
                .build();

        System.out.println("Enviando áudio para o Whisper...");

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Resposta do Whisper: " + response.body());

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Erro ao transcrever áudio. Status: "
                            + response.statusCode()
                            + " - "
                            + response.body()
            );
        }

        return response.body();
    }
}