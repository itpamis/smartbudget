package com.augusto.smartbudget.infrastructure.ai;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TtsClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public byte[] synthesize(String text)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5001/synthesize"))
                .header("Content-Type", "text/plain; charset=UTF-8")
                .POST(
                        HttpRequest.BodyPublishers.ofString(text)
                )
                .build();

        System.out.println("Enviando texto para o Piper...");

        HttpResponse<byte[]> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofByteArray()
        );

        System.out.println(
                "Resposta do Piper: "
                        + response.statusCode()
                        + " - "
                        + response.body().length
                        + " bytes"
        );

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Erro ao gerar áudio. Status: "
                            + response.statusCode()
            );
        }

        return response.body();
    }
}