package com.Aluracursos.Literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    private static final String BASE_URL = "https://gutendex.com/books/";

    public String fiindBookByTitle(String titulo) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?search=" + titulo.replace(" ", "+")))
                .build();

        {
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error al consultar la API: " + e.getMessage(), e);
            }
        }
    }
}
