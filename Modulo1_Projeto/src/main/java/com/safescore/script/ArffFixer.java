package com.safescore.script;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ArffFixer {

    public static void corrigirArquivoArff(String entrada, String saida) {
        try (
                BufferedReader reader = Files.newBufferedReader(Paths.get(entrada));
                PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(saida)))
        ) {
            String linha;
            boolean dentroDoData = false;
            int linhaAtual = 0;

            while ((linha = reader.readLine()) != null) {
                linhaAtual++;

                if (!dentroDoData) {
                    if (linha.trim().equalsIgnoreCase("@attribute score numeric")) {
                        writer.println("@attribute score {baixo,medio,alto}");
                        continue;
                    }
                    writer.println(linha);
                    if (linha.trim().equalsIgnoreCase("@data")) {
                        dentroDoData = true;
                    }
                    continue;
                }

                if (linha.trim().isEmpty()) continue;

                String[] colunas = linha.trim().split(",");
                if (colunas.length != 10) {
                    System.out.println("⚠️ Linha ignorada (colunas ≠ 10): " + linhaAtual);
                    continue;
                }

                List<String> dados = new ArrayList<>(Arrays.asList(colunas));

                try {
                    double score = Double.parseDouble(dados.get(9));
                    String categoria;
                    if (score <= 400) categoria = "baixo";
                    else if (score <= 700) categoria = "medio";
                    else categoria = "alto";

                    dados.set(9, categoria); // substitui valor do score
                    writer.println(String.join(",", dados));
                } catch (NumberFormatException e) {
                    System.out.println("❌ Erro ao converter score na linha " + linhaAtual + ": " + colunas[9]);
                }
            }

            System.out.println("✅ Arquivo categorizado gerado em: " + saida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        corrigirArquivoArff(
                "Modulo1_Projeto/src/main/sources/usuario.arff",
                "Modulo1_Projeto/src/main/sources/usuario_categorizado.arff"
        );
    }
}
