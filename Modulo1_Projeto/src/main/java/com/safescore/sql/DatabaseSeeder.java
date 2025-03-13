package com.safescore.sql;

import net.datafaker.Faker;

import java.util.*;

public class DatabaseSeeder {
  static Faker faker = new Faker(Locale.of("pt", "BR"));

  public static void test() {

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

    System.out.println(calculadorProbabilidade(4, escolaridades));

    //estado civil
    String[] estadoCivil =
            {"Solteiro", "Casado", " Viuvo", "Divorciado"};
    System.out.println(dadoAleatorio(estadoCivil));

    //historicoCredito
    int parcelasAPagar = faker.number().numberBetween(0, 100);
    int valorCredito = faker.number().numberBetween(1, 100);
    for (int i = 0; i < faker.number().numberBetween(0, 6); i++) {
      valorCredito *= 10;
    }
    ;
    int parcela = valorCredito / parcelasAPagar;
    int parcelasRestantes = faker.number().numberBetween(0, parcelasAPagar);
    int creditorestante = parcela * (parcelasAPagar - parcelasRestantes);
    Boolean estaInandinplente = faker.bool().bool();
    int mesesAtrasado = estaInandinplente ? Math.min(faker.number().numberBetween(0, 100), faker.number().numberBetween(0, 100)) : 0;
    System.out.println("ParcelaRestante: " + parcelasRestantes);
    System.out.println("Parcela: " + parcela);
    System.out.println("MesesAtrasado: " + mesesAtrasado);
    System.out.println("EstaInadinplente: " + estaInandinplente);
    System.out.println("CreditoRestante: " + creditorestante);
    System.out.println("Valor Total: " + valorCredito);
    //e cpf

    //patrimonio
    //1/4

    int nivelRenda = faker.number().numberBetween(1, 10);
    int saldo =  sorteadorDeRenda(nivelRenda);
    System.out.println("Investimentos: " + (nivelRenda < 8 ? 0 : sorteadorDeRenda(nivelRenda)));
    System.out.println("Bens: " + (sorteadorDeRenda(nivelRenda) + saldo));
    System.out.println("Saldo: " + saldo);

    //ecpf


  }


  public static int sorteadorDeRenda(int nivelRenda) {
    return switch (nivelRenda) {
      case 1, 2, 3, 4, 5, 6, 7 -> baixoValorMonetario();
      case 8, 9 -> medioValorMonetario();
      case 10 -> altoValorMonetario();
      default -> 0;
    };
  }

  public static int baixoValorMonetario() {
    return Math.min(faker.number().numberBetween(0, 100000), faker.number().numberBetween(0, 100000));
  }

  public static int medioValorMonetario() {
    return Math.min(faker.number().numberBetween(0, 700000), faker.number().numberBetween(0, 700000));
  }

  public static int altoValorMonetario() {
    return Math.min(faker.number().numberBetween(0, 10000000), faker.number().numberBetween(0, 10000000));
  }


  public static String dadoAleatorio(String[] opcoes) {
    return opcoes[faker.number().numberBetween(0, opcoes.length - 1)];
  }

  public static String calculadorProbabilidade(int opcaoComum, String[] opcoes) {
    double[] probabilidades = new double[opcoes.length];
    int distanciaMaxima = Math.max(opcaoComum, opcoes.length - opcaoComum - 1);
    for (int i = 0; i < opcoes.length; i++) {
      int distancia = Math.abs(i - opcaoComum);
      probabilidades[i] = distanciaMaxima - distancia + 1;
    }

    double total = 0.0;
    for (double peso : probabilidades) {
      total += peso;
    }
    for (int i = 0; i < opcoes.length; i++) {
      probabilidades[i] /= total;
    }
    double random = Math.random();
    double acc = 0.0;

    for (int i = 0; i < opcoes.length; i++) {
      acc += probabilidades[i];
      if (random <= acc) {
        return opcoes[i];
      }
    }
    return null;
  }

  public static void main(String[] args) {
    test();
  }
}

