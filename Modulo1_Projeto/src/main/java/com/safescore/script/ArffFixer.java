package com.safescore.script;

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class ArffFixer {

    public static void corrigirArquivoArff(String entrada, String saida) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(entrada));
             PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(saida)))) {

            String linha;
            boolean dentroDoData = false;
            int linhaAtual = 0;

            Pattern virgulaDecimal = Pattern.compile("(?<=\\d),(?=\\d)");

            while ((linha = reader.readLine()) != null) {
                linhaAtual++;

                // Mantém cabeçalho como está
                if (!dentroDoData) {
                    writer.println(linha);
                    if (linha.trim().equalsIgnoreCase("@data")) {
                        dentroDoData = true;
                    }
                    continue;
                }

                // Corrige linhas @data
                if (linha.trim().isEmpty()) continue;

                // Substitui vírgula decimal por ponto
                String linhaCorrigida = virgulaDecimal.matcher(linha).replaceAll(".");

                // Divide e valida número de colunas
                String[] colunas = linhaCorrigida.split(",");
                if (colunas.length != 10) {
                    System.out.println("⚠️ Linha ignorada (colunas ≠ 10) [linha " + linhaAtual + "]: " + linhaCorrigida);
                    continue;
                }

                writer.println(linhaCorrigida);
            }

            System.out.println("✅ Arquivo corrigido gerado com sucesso em: " + saida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        corrigirArquivoArff(
                "Modulo1_Projeto/src/main/sources/usuario.arff",            // arquivo original
                "Modulo1_Projeto/src/main/sources/usuario_corrigido.arff"   // saída corrigida
        );
    }
}
