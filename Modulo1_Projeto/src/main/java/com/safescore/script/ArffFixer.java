package com.safescore.script;

import java.io.*;
import java.util.*;

public class ArffFixer {
    public static void main(String[] args) {
        String arquivoEntrada = "Modulo1_Projeto/src/main/sources/usuarios.arff";
        String arquivoSaida = "Modulo1_Projeto/src/main/sources/usuarios_corrigido.arff";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida))) {

            String linha;
            boolean lendoDados = false;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();

                if (linha.isEmpty()) {
                    bw.write("\n");
                    continue;
                }

                if (!lendoDados) {
                    bw.write(linha + "\n");
                    if (linha.toLowerCase().startsWith("@data")) {
                        lendoDados = true;
                    }
                    continue;
                }

                String linhaCorrigida = corrigirLinha(linha);
                if (linhaCorrigida != null) {
                    bw.write(linhaCorrigida + "\n");
                }
            }

            System.out.println("Arquivo corrigido gerado com sucesso: " + arquivoSaida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String corrigirLinha(String linha) {
        try {
            List<String> campos = new ArrayList<>();
            int pos = 0;
            int tipoColuna = 0;

            // Define a sequência de tipos: "string", "int", "string", "string", "int", etc.
            String[] tipos = {
                    "string", "int", "string", "string", "int",
                    "int", "string", "string", "int", "float",
                    "float", "float", "float", "float", "int",
                    "float", "int", "float", "int"
            };

            while (tipoColuna < tipos.length && pos < linha.length()) {
                if (tipos[tipoColuna].equals("string")) {
                    if (linha.charAt(pos) == '\'') {
                        int fim = linha.indexOf('\'', pos + 1);
                        if (fim == -1) return null; // erro
                        campos.add("'" + linha.substring(pos + 1, fim) + "'");
                        pos = fim + 1;
                        if (pos < linha.length() && linha.charAt(pos) == ',') pos++;
                    } else {
                        return null; // esperava string entre aspas
                    }
                } else if (tipos[tipoColuna].equals("int")) {
                    int fim = pos;
                    while (fim < linha.length() && (Character.isDigit(linha.charAt(fim)))) {
                        fim++;
                    }
                    if (fim == pos) return null; // erro
                    campos.add(linha.substring(pos, fim));
                    pos = fim;
                    if (pos < linha.length() && linha.charAt(pos) == ',') pos++;
                } else if (tipos[tipoColuna].equals("float")) {
                    int fim = pos;
                    StringBuilder numero = new StringBuilder();
                    boolean pontoEncontrado = false;
                    while (fim < linha.length()) {
                        char c = linha.charAt(fim);
                        if (Character.isDigit(c)) {
                            numero.append(c);
                        } else if (c == ',' || c == '.') {
                            if (pontoEncontrado) break; // terminou esse float
                            numero.append('.');
                            pontoEncontrado = true;
                        } else {
                            break;
                        }
                        fim++;
                    }
                    if (numero.length() == 0) return null; // erro
                    campos.add(numero.toString());
                    pos = fim;
                    if (pos < linha.length() && linha.charAt(pos) == ',') pos++;
                }
                tipoColuna++;
            }

            if (campos.size() != 19) {
                System.err.println("Linha ignorada (colunas = " + campos.size() + "): " + linha);
                return null;
            }

            return String.join(",", campos);

        } catch (Exception e) {
            System.err.println("Erro ao corrigir linha: " + linha);
            return null;
        }
    }
}


