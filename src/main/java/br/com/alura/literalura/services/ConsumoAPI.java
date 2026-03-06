package br.com.alura.literalura.services;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ConsumoAPI {
    private HttpClient client = HttpClient.newHttpClient();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConverteDados conversor = new ConverteDados();

    public String obterDados(String busca) {
        String buscaFormatada = URLEncoder.encode(busca, StandardCharsets.UTF_8);
        String endereco = URL_BASE + buscaFormatada;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}