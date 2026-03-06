package br.com.alura.literalura.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    private HttpClient client = HttpClient.newHttpClient();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public String obterDados(String busca) {
        String endereco = URL_BASE + busca;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}