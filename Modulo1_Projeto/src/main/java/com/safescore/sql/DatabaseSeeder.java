package com.safescore.sql;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DatabaseSeeder {
  static Faker faker = new Faker(Locale.of("pt", "BR"));

  public static void test() {
    LocalDate data = faker.date().birthday(18, 100).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int[] nivelAltoValores = {1000, 7000, 100000};
    int nivel = faker.number().numberBetween(1, 10);
    int saldoTotal = sorteadorDeRenda(nivel, nivelAltoValores);

    String cpfUsuario = criarUsuarioAleatorio(data);

    if(faker.bool().bool()){
      int totalCreditos  = faker.number().numberBetween(1,5);
      for (int i = 0; i <= totalCreditos; i++) {
        criarHistoricoCredito(cpfUsuario);
      }
    }
;

    criarPatrimonioAleatorio(cpfUsuario, nivel, saldoTotal, nivelAltoValores);

    criarEmpregoAleatorio(cpfUsuario, nivel, data);

    int totalEnderecos = faker.number().numberBetween(1, 5);
    String estadoNascenca = criarEnderecoAleatorio(cpfUsuario, data, true, totalEnderecos == 1, faker.address().state());
    if (totalEnderecos != 1) {
      while (totalEnderecos < 5) {
        criarEnderecoAleatorio(cpfUsuario, data, false, totalEnderecos == 4, estadoNascenca);
        totalEnderecos++;
      }
    }

    criarTransacaoAleatorio(cpfUsuario, saldoTotal, nivel, nivelAltoValores);

  }

  public static String criarUsuarioAleatorio(LocalDate dataNascimento) {
    //usuario
    System.out.println("---------USUARIO---------");
    String cpf = faker.cpf().valid();
    System.out.println(cpf);
    System.out.println("Nome: " + faker.name().firstName() + " " + faker.name().lastName());
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
    return cpf;
  }

  public static void criarHistoricoCredito(String cpf) {

    //historicoCredito
    System.out.println("---------HISTORICO DE CREDITO---------");
    int parcelasAPagar = faker.number().numberBetween(0, 100);
    double valorCredito = faker.number().numberBetween(1, 100);
    for (int i = 0; i < faker.number().numberBetween(0, 6); i++) {
      valorCredito *= 10;
    }

    double parcela = valorCredito / parcelasAPagar;
    int parcelasRestantes =  faker.bool().bool() ? 0 : faker.number().numberBetween(0, parcelasAPagar);
    double creditorestante = parcela * (parcelasAPagar - parcelasRestantes);
    boolean estaInandinplente = parcelasRestantes == 0 ? false : faker.bool().bool();
    int mesesAtrasado = estaInandinplente ? Math.min(faker.number().numberBetween(0, 100), faker.number().numberBetween(0, 100)) : 0;
    System.out.println("ParcelaRestante: " + parcelasRestantes);
    System.out.println("Parcela: " + Math.round(parcela));
    System.out.println("MesesAtrasado: " + mesesAtrasado);
    System.out.println("EstaInadinplente: " + estaInandinplente);
    System.out.println("CreditoRestante: " + Math.round(creditorestante));
    System.out.println("Valor Total: " + Math.round(valorCredito));
    //e cpf
  }

  public static void criarPatrimonioAleatorio(String cpf, int nivelRenda, int saldo, int[] valoresAltosNivelRenda) {
    //patrimonio
    //1/4
    System.out.println("---------PATRIMONIO---------");

    System.out.println("Investimentos: " + (nivelRenda < 8 ? 0 : sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda)));
    System.out.println("Bens: " + (sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda) + saldo));
    System.out.println("Saldo: " + saldo);
    //e cpf

  }

  public static void criarTransacaoAleatorio(String cpf, int saldo, int nivelRenda, int[] valoresAltosNivelRenda) {
    //transacao
    System.out.println("---------TRANSACAO---------");
    System.out.println("Salario Incluso: " + faker.bool().bool());
    int anosNoBanco = faker.number().numberBetween(0, 6);
    int anoAtual = 2025;
    System.out.println("Data pagamento: " + geradorData(10, faker.number().numberBetween(1, 12), anoAtual - anosNoBanco));
    int entrada = valorMonetario(saldo);
    System.out.println("Valor entrada: " + entrada);
    System.out.println("Valor Saida: " + (faker.bool().bool() ? sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda) : valorMonetario(entrada)));

  }

  public static void criarEmpregoAleatorio(String cpf, int nivelRenda, LocalDate dataNascimento) {
    //emprego
    int[] valoresSalarioNivelRenda = {35, 200, 10000};
    int diaInicioEmprego = faker.number().numberBetween(1, 31);
    int mesInicioEmprego = faker.number().numberBetween(1, 12);
    int anoInicioEmprego = faker.number().numberBetween(dataNascimento.getYear() + 18, 2025);

    LocalDate dataInicioEmprego = geradorData(diaInicioEmprego, mesInicioEmprego, anoInicioEmprego);
    System.out.println("---------EMPREGO---------");

    System.out.println("Salario: " + Math.max(1518, sorteadorDeRenda(nivelRenda, valoresSalarioNivelRenda)));
    System.out.println("DataInicio: " + dataInicioEmprego);
    System.out.println("DataFim: " + geradorDataFim(dataInicioEmprego));

    String[] vinculoTrabalista = {
            "Estagio",
            "CLT",
            "Autonomo",
            "PJ",
    };

    System.out.println("VinculoTrabalista: " + calculadorProbabilidade(1, vinculoTrabalista));

  }

  public static String criarEnderecoAleatorio(String cpf, LocalDate dataNascimento, Boolean primeiroEndereco, Boolean ultimoEndereco, String estadoUltimoEndereco) {
    System.out.println("---------ENDERECO---------");

    String cep = faker.bool().bool() ? faker.address().postcode() : null;
    String estado = faker.bool().bool() ? faker.address().state() : estadoUltimoEndereco;

    //cep
    System.out.println("CEP: " + cep);
    System.out.println("Numero: " + Math.min(faker.number().numberBetween(1, 2500), faker.number().numberBetween(1, 2500)));
    System.out.println("Estado: " + estado);
    //contratoResidencial
    int diaInicioEndereco = faker.number().numberBetween(1, 31);
    int mesInicioEndereco = faker.number().numberBetween(1, 12);
    int anoInicioEndereco = faker.number().numberBetween(dataNascimento.getYear(), 2025);
    LocalDate dataInicioEndereco = primeiroEndereco ? geradorData(dataNascimento.getDayOfMonth(), dataNascimento.getMonthValue(), dataNascimento.getYear()) : geradorData(diaInicioEndereco, mesInicioEndereco, anoInicioEndereco);
    System.out.println("DataInicio: " + dataInicioEndereco);
    System.out.println("DataFim: " + (ultimoEndereco ?  null : geradorDataFim(dataInicioEndereco)));

    return estado;
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
    return LocalDate.of(ano, mes, dia);
  }

  public static LocalDate geradorDataFim(LocalDate dataInicio) {
    long diasAdicionais = faker.number().numberBetween(1, (int) ChronoUnit.DAYS.between(dataInicio, LocalDate.of(2025, 12, 31)) + 1);
    return dataInicio.plusDays(diasAdicionais);
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

