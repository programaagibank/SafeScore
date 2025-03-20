package com.safescore.sql;


import net.datafaker.Faker;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DatabaseSeeder {
  static Faker faker = new Faker(Locale.of("pt", "BR"));

  public static void test() {
    //VariaveisGerais
    LocalDate data = faker.date().birthday(18, 100).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    int[] nivelAltoValores = {1000, 7000, 100000};
    int nivel = faker.number().numberBetween(1, 10);
    int saldoTotal = sorteadorDeRenda(nivel, nivelAltoValores);

    String cpfUsuario = criarUsuarioAleatorio(data);

    if (faker.bool().bool()) {
      int totalCreditos = faker.number().numberBetween(1, 5);
      for (int i = 0; i <= totalCreditos; i++) {
        criarHistoricoCredito(cpfUsuario);
      }
    }

    criarPatrimonioAleatorio(cpfUsuario, nivel, saldoTotal, nivelAltoValores);

    LocalDate comecoEmprego = data.plusYears(18);
    LocalDate fimEmprego;
    LocalDate dataAposentadoria = data.plusYears(70);
    dataAposentadoria = dataAposentadoria.isAfter(LocalDate.now()) ? LocalDate.now() : dataAposentadoria;
    int totalEmpregos = faker.number().numberBetween(1, 6);
    for (int i = 0; i < totalEmpregos; i++) {
      boolean ultimoEmprego = (i == totalEmpregos - 1);

      fimEmprego = criarEmpregoAleatorio(
              cpfUsuario,
              nivel,
              comecoEmprego,
              ultimoEmprego,
              data
      );
      if (fimEmprego == null) {
        break;
      }
      if (ultimoEmprego) {
        break;
      } else {
        if (!fimEmprego.isAfter(dataAposentadoria)) {
          comecoEmprego = fimEmprego;
        } else {
          break;
        }


      }
    }


    int totalEnderecos = faker.number().numberBetween(1, 5);
    LocalDate inicioEndereco = data;

    String estadoNascenca = faker.address().state();
    LocalDate dataFimEndereco;
    if (totalEnderecos != 1) {
      for (int i = 1; i < totalEnderecos; i++) {
        boolean ultimoEndereco = (i == totalEnderecos - 1);
        dataFimEndereco = criarEnderecoAleatorio(
                cpfUsuario, inicioEndereco, false, ultimoEndereco, estadoNascenca
        );
        if (dataFimEndereco == null) {
          break;
        }
        inicioEndereco = dataFimEndereco;
      }
    }

    int anosNoBanco = faker.number().numberBetween(0, 6);
    LocalDate comecoNoBanco = LocalDate.now().minusYears(anosNoBanco);

    int mesesNoBanco = faker.number().numberBetween(-12,0) + (int) ChronoUnit.MONTHS.between(comecoNoBanco, LocalDate.now());

    for (int i = 0; i < mesesNoBanco; i++) {
      LocalDate dataTransacao = geradorData(5, comecoNoBanco.plusMonths(i));
      criarTransacaoAleatorio(cpfUsuario, saldoTotal, nivel, nivelAltoValores, dataTransacao);
    }


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
    int parcelasRestantes = faker.bool().bool() ? 0 : faker.number().numberBetween(0, parcelasAPagar);
    double creditorestante = parcela * (parcelasAPagar - parcelasRestantes);
    boolean estaInandinplente = parcelasRestantes != 0 && faker.bool().bool();
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
    System.out.println("---------PATRIMONIO---------");


    System.out.println("Investimentos: " + (nivelRenda < 8 ? 0 : sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda)));
    System.out.println("Bens: " + (sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda) + saldo));
    System.out.println("Saldo: " + saldo);
    //e cpf


  }


  public static void criarTransacaoAleatorio(String cpf, int saldo, int nivelRenda, int[] valoresAltosNivelRenda, LocalDate dataTransacao) {
    //transacao
    System.out.println("---------TRANSACAO---------");
    System.out.println("Salario Incluso: " + faker.bool().bool());
    System.out.println("Data pagamento: " + dataTransacao);
    int entrada = valorMonetario(saldo);
    System.out.println("Valor entrada: " + entrada);
    System.out.println("Valor Saida: " + (faker.bool().bool() ? sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda) : valorMonetario(entrada)));

  }


  public static LocalDate criarEmpregoAleatorio(String cpf, int nivelRenda, LocalDate comecoEmprego, Boolean empregoAtual, LocalDate dataNascimento) {
    int[] valoresSalarioNivelRenda = {35, 200, 10000};

    LocalDate dataAposentadoria = dataNascimento.plusYears(70);
    if (comecoEmprego.isAfter(dataAposentadoria)) {
      return null;
    }
    comecoEmprego = comecoEmprego.plusDays(faker.number().numberBetween(1, 60));
    LocalDate fimEmprego = geradorDataFim(comecoEmprego);

    if (fimEmprego != null) {
      if (fimEmprego.isAfter(dataAposentadoria)) {
        fimEmprego = dataAposentadoria;
      }
    } else {
      if (dataAposentadoria.isBefore(LocalDate.now())) {
        fimEmprego = dataAposentadoria;
      }
    }

    System.out.println("---------EMPREGO---------");
    String[] vinculosTrabalistas = {"Estagio", "CLT", "Autonomo", "PJ"};
    String vinculoTrabalista = calculadorProbabilidade(1, vinculosTrabalistas);

    int salario = Objects.equals(vinculoTrabalista, "Estagio") ?
            faker.number().numberBetween(1528, 3500) :
            Math.max(1518, Math.max(sorteadorDeRenda(nivelRenda, valoresSalarioNivelRenda), sorteadorDeRenda(nivelRenda, valoresSalarioNivelRenda)));

    System.out.println("Salario: " + salario);
    System.out.println("DataInicio: " + comecoEmprego);
    System.out.println("DataFim: " + fimEmprego);
    System.out.println("VinculoTrabalista: " + vinculoTrabalista);

    return fimEmprego;
  }

  public static LocalDate criarEnderecoAleatorio(
          String cpf, LocalDate dataInicio, Boolean primeiroEndereco, Boolean ultimoEndereco, String estadoUltimoEndereco) {

    System.out.println("---------ENDERECO---------");

    String cep = faker.bool().bool() ? faker.address().postcode() : null;
    String estado = faker.bool().bool() ? faker.address().state() : estadoUltimoEndereco;

    System.out.println("CEP: " + cep);
    System.out.println("Numero: " + Math.min(faker.number().numberBetween(1, 2500), faker.number().numberBetween(1, 2500)));
    System.out.println("Estado: " + estado);

    LocalDate dataInicioEndereco = dataInicio.plusDays(faker.number().numberBetween(1, 60));

    LocalDate dataFimEndereco = ultimoEndereco ? null : geradorDataFim(dataInicioEndereco);

    System.out.println("DataInicio: " + dataInicioEndereco);
    System.out.println("DataFim: " + (dataFimEndereco == null ? "Atual" : dataFimEndereco));

    return dataFimEndereco;
  }


  public static int sorteadorDeRenda(int nivelRenda, int[] valores) {
    return switch (nivelRenda) {
      case 1, 2, 3, 4, 5, 6, 7 -> valorMonetario(valores[0] * 100);
      case 8, 9 -> valorMonetario(valores[1] * 100);
      case 10 -> valorMonetario(valores[2] * 100);
      default -> 0;
    };
  }

  public static LocalDate geradorData(int dia, LocalDate dataBase) {
    return LocalDate.of(dataBase.getYear(), dataBase.getMonth(), dia);
  }

  public static LocalDate geradorDataFim(LocalDate dataInicio) {
    LocalDate dataFim = dataInicio.plusDays(faker.bool().bool() ? faker.number().numberBetween(30, 365 * 4) : faker.number().numberBetween(365 * 4, 365 * 50));
    return dataFim.getYear() > LocalDate.now().getYear() ? null : dataFim;
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





