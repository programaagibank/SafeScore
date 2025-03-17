package com.safescore.sql;

import net.datafaker.Faker;

//import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
import java.util.*;

public class DatabaseSeeder {
  static Faker faker = new Faker(Locale.of("pt", "BR"));

  public static void test() {

    //usuario
    LocalDate dataNascimento = faker.date().birthday(18, 100).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    System.out.println("---------USUARIO---------");
    System.out.println("Nome: " + faker.name().name());
    System.out.println("Aniversario: " + dataNascimento);

    System.out.println("Dependentes: " + faker.number().numberBetween(0, 5));

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

    System.out.println("Escolaridade: " + calculadorProbabilidade(4, escolaridades));

    //estado civil
    String[] estadoCivil =
            {"Solteiro", "Casado", " Viuvo", "Divorciado"};
    System.out.println("Estado civil: " + dadoAleatorio(estadoCivil));

    //historicoCredito
    System.out.println("---------HISTORICO DE CREDITO---------");
    int parcelasAPagar = faker.number().numberBetween(0, 100);
    int valorCredito = faker.number().numberBetween(1, 100);
    for (int i = 0; i < faker.number().numberBetween(0, 6); i++) {
      valorCredito *= 10;
    }

    int parcela = valorCredito / parcelasAPagar;
    int parcelasRestantes = faker.number().numberBetween(0, parcelasAPagar);
    int creditorestante = parcela * (parcelasAPagar - parcelasRestantes);
    boolean estaInandinplente = faker.bool().bool();
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
    System.out.println("---------PATRIMONIO---------");
    int[] valoresAltosNivelRenda = {1000, 7000, 100000};
    int nivelRenda = faker.number().numberBetween(1, 10);
    int saldo = sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda);
    System.out.println("Investimentos: " + (nivelRenda < 8 ? 0 : sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda)));
    System.out.println("Bens: " + (sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda) + saldo));
    System.out.println("Saldo: " + saldo);
    //e cpf

    //score
    //?

    //transacao
    System.out.println("---------TRANSACAO---------");
    System.out.println("Salario Incluso: " + faker.bool().bool());
//    Timestamp dataRegistroTransacao = new Timestamp(System.currentTimeMillis());
    int anosNoBanco = faker.number().numberBetween(0, 6);
    int anoAtual = 2025;
    System.out.println("Data pagamento: " + geradorData(10, faker.number().numberBetween(1, 12), anoAtual - anosNoBanco));
    int entrada = valorMonetario(saldo);
    System.out.println("Valor entrada: " + entrada);
    System.out.println("Valor Saida: " + (faker.bool().bool() ? sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda) : sorteadorDeRenda(entrada, valoresAltosNivelRenda)));

    //emprego
    int[] valoresSalarioNivelRenda = {35, 200, 10000};
    int diaInicioEmprego = faker.number().numberBetween(1, 31);
    int mesInicioEmprego = faker.number().numberBetween(1, 12);
    int anoInicioEmprego = faker.number().numberBetween(dataNascimento.getYear() + 18, 2025);

    LocalDate dataInicioEmprego = geradorData(diaInicioEmprego, mesInicioEmprego, anoInicioEmprego);
    System.out.println("---------EMPREGO---------");

    System.out.println("Salario: "+Math.max(1518, sorteadorDeRenda(nivelRenda, valoresSalarioNivelRenda)));
    System.out.println("DataInicio: "+dataInicioEmprego);
    System.out.println("DataFim: "+geradorDataFim(dataInicioEmprego));
//
  }


  public static int sorteadorDeRenda(int nivelRenda, int[] valores) {
    return switch (nivelRenda) {
      case 1, 2, 3, 4, 5, 6, 7 -> valorMonetario(valores[0] * 100);
      case 8, 9 -> valorMonetario(valores[1] * 100);
      case 10 -> valorMonetario(valores[2] * 100);
      default -> 0;
    };
  }

  public static LocalDate geradorData(int dia, int mes, int ano) {
    return LocalDate.of(ano,mes,dia);
  }


  public static LocalDate geradorDataFim(LocalDate dataInicio) {
    int diaFimEmprego;
    int mesFimEmprego;
    int anoFimEmprego;
    do {

      diaFimEmprego = faker.number().numberBetween(1, 31);
      mesFimEmprego = faker.number().numberBetween(1, 12);
      anoFimEmprego = faker.number().numberBetween(dataInicio.getYear(), 2025);
    } while ((anoFimEmprego != dataInicio.getYear() || mesFimEmprego != dataInicio.getMonthValue() || diaFimEmprego <= dataInicio.getDayOfMonth()) && (anoFimEmprego != dataInicio.getYear() || mesFimEmprego <= dataInicio.getMonthValue()));


    return LocalDate.of(anoFimEmprego,mesFimEmprego,diaFimEmprego);


  }

  public static int valorMonetario(int maximo) {
    return Math.min(faker.number().numberBetween(0, maximo), faker.number().numberBetween(0, maximo));
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

