package com.safescore.service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuscadorEnderecoPorCep {

  public static String buscarEstadoPorCEP(String cep) {
    String url = "https://viacep.com.br/ws/" + cep + "/json/";

    try {
      // Criando o HttpClient
      HttpClient client = HttpClient.newHttpClient();

      // Criando a requisição
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .GET()
              .build();

      // Enviando a requisição e recebendo a resposta
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      // Extrai a UF do JSON retornado
      return extrairUF(response.body());

    } catch (Exception e) {
      return "Erro ao buscar CEP: " + e.getMessage();
    }
  }

  private static String extrairUF(String json) {
    Pattern pattern = Pattern.compile("\"uf\":\\s*\"(\\w{2})\"");
    Matcher matcher = pattern.matcher(json);
    return matcher.find() ? matcher.group(1) : "CEP inválido!";
  }
}
