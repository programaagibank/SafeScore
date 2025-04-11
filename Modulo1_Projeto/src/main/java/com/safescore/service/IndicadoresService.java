package com.safescore.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IndicadoresService {

    public static String getTaxaSelic() {
        return consultarApiBCB("https://api.bcb.gov.br/dados/serie/bcdata.sgs.432/dados/ultimos/1?formato=json", "Taxa Selic");
    }

    public static String getInflacaoIPCA() {
        return consultarApiBCB("https://api.bcb.gov.br/dados/serie/bcdata.sgs.433/dados/ultimos/1?formato=json", "Inflação IPCA");
    }

    private static String consultarApiBCB(String urlString, String nomeIndicador) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder resposta = new StringBuilder();
                String linha;

                while ((linha = reader.readLine()) != null) {
                    resposta.append(linha);
                }
                reader.close();

                String json = resposta.toString();
                String valor = json.split("\"valor\":")[1].replaceAll("[^0-9.,]", "");

                return valor + "%";
            } else {
                return "Falha ao obter " + nomeIndicador + ". Código HTTP: " + conn.getResponseCode();
            }

        } catch (Exception e) {
            return "Erro ao obter " + nomeIndicador + ": " + e.getMessage();
        }
    }
}
