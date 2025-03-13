package com.safescore.sql;

import net.datafaker.Faker;

import java.util.*;

public class DatabaseSeeder {
  public static void test() {
    Faker faker = new Faker(Locale.of("pt", "BR"));

    //usuario
    System.out.println(faker.name().name());
    System.out.println(faker.date().birthday(18, 100));
    System.out.println(faker.number().numberBetween(0, 5));

    //escolaridade
    String[] escolaridades = {
            "Ensino Médio Incompleto",
            "Sem Escolaridade",
            "Ensino Fundamental Completo",
            "Ensino Fundamental Incompleto",
            "Ensino Médio Completo",
            "Superior Completo",
            "Técnico",
            "Superior Incompleto",
            "Pós-graduação",
            "Mestrado",
            "Doutorado",
            "Pós-doutorado"
    };

    double[] probabilidades = calculadorProbabilidade(escolaridades.length, 4);
    double random = Math.random();
    double cumulative = 0.0;

    for (int i = 0; i < escolaridades.length; i++) {
      cumulative += probabilidades[i];
      if (random <= cumulative) {
        System.out.println("Escolaridade: " + escolaridades[i]);
        break;
      }
    }

  }

  public static double[] calculadorProbabilidade(int tamanhoOpcoes, int opcaoComum) {
    double[] probabilidades = new double[tamanhoOpcoes];
    int distanciaMaxima = Math.max(opcaoComum, tamanhoOpcoes - opcaoComum - 1);
    for (int i = 0; i < tamanhoOpcoes; i++) {
      int distancia = Math.abs(i - opcaoComum);
      probabilidades[i] = distanciaMaxima - distancia + 1;
    }

    double total = 0.0;
    for (double peso : probabilidades) {
      total += peso;
    }
    for (int i = 0; i < tamanhoOpcoes; i++) {
      probabilidades[i] /= total;
    }
    return probabilidades;
  }


  public static void main(String[] args) {
    test();
  }
}

