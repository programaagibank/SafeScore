package com.safescore.sql;

import net.datafaker.Faker;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseSeeder {
  static Faker faker = new Faker(Locale.of("pt", "BR"));

  public static void test() {
    //VariaveisGerais
    LocalDate data = faker.date().birthday(18, 100).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    int[] nivelAltoValores = {1000, 7000, 100000};
    int nivel = faker.number().numberBetween(1, 10);
    int saldoTotal = sorteadorDeRenda(nivel, nivelAltoValores);

    String cpfUsuario = (String) criarUsuarioAleatorio(data)[0];
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

      fimEmprego = (LocalDate) criarEmpregoAleatorio(
              cpfUsuario,
              nivel,
              comecoEmprego,
              ultimoEmprego,
              data
      )[2];
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
    String estadoNascenca = faker.address().stateAbbr();
    LocalDate dataFimEndereco;
    Integer latestIdEndereco = null;
    Object[] enderecoResult;

    if (totalEnderecos != 1) {
      for (int i = 0; i < totalEnderecos; i++) {
        boolean ultimoEndereco = (i == totalEnderecos - 1);
        enderecoResult = criarEnderecoAleatorio(
                cpfUsuario, inicioEndereco, ultimoEndereco, estadoNascenca
        );
        latestIdEndereco = (Integer) enderecoResult[0];
        dataFimEndereco = (LocalDate) enderecoResult[2];

        if (dataFimEndereco != null) {
          inicioEndereco = dataFimEndereco;
        }
        if (latestIdEndereco != null && latestIdEndereco > 0) {

          LocalDate enderecoInicialContratoResidencial = (LocalDate) enderecoResult[1];
          LocalDate enderecoFinalContratoResidencial = (LocalDate) enderecoResult[2];
          enderecoFinalContratoResidencial = enderecoFinalContratoResidencial != null ? (enderecoFinalContratoResidencial.isAfter(enderecoInicialContratoResidencial) ? enderecoFinalContratoResidencial : null) : enderecoFinalContratoResidencial;
          create("contratoResidencial",
                  new String[]{"dataInicialEndereco", "dataFinalEndereco", "cpf", "idTipoContratoResidencial", "idEndereco"},
                  new Object[]{enderecoInicialContratoResidencial,enderecoFinalContratoResidencial, cpfUsuario, 1, latestIdEndereco});
        }
      }
    } else {
      enderecoResult = criarEnderecoAleatorio(
              cpfUsuario, inicioEndereco, true, estadoNascenca);
      create("contratoResidencial",
              new String[]{"dataInicialEndereco", "dataFinalEndereco", "cpf", "idTipoContratoResidencial", "idEndereco"},
              new Object[]{enderecoResult[1], enderecoResult[2], cpfUsuario, 1, enderecoResult[0]});

    }



//TODO: AJUSTAR O TIPO CONTRATO RESIDENCIA

    int anosNoBanco = faker.number().numberBetween(0, 3);
    LocalDate comecoNoBanco = LocalDate.now().minusYears(anosNoBanco);

    int mesesNoBanco = faker.number().numberBetween(-12, 0) + (int) ChronoUnit.MONTHS.between(comecoNoBanco, LocalDate.now());

    for (int i = 0; i < mesesNoBanco; i++) {
      LocalDate dataTransacao = geradorData(5, comecoNoBanco.plusMonths(i));
      criarTransacaoAleatorio(cpfUsuario, saldoTotal, nivel, nivelAltoValores, dataTransacao);
    }

  }


  public static Object[] criarUsuarioAleatorio(LocalDate dataNascimento) {
    String cpf = faker.cpf().valid();
    String nome = faker.name().firstName() + " " + faker.name().lastName();
    System.out.println("\nCriando " + nome + " com CPF: " + cpf);
    int dependentes = faker.number().numberBetween(0, 5);

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

    String[] estadoCivil =
            {"Solteiro", "Casado", " Viuvo", "Divorciado"};

//TODO:AJUSTAR OS IDS DE ESCOLARIDADE E ESTADO CIVIL
    create("usuario",
            new String[]{"cpf", "nome", "dataNascimento", "dependentes", "idEscolaridade", "idEstadoCivil"},
            new Object[]{cpf, nome, dataNascimento, dependentes, 1, 1});

    return new Object[]{cpf, nome, dataNascimento, dependentes, 1, 1};
  }

  public static Object[] criarHistoricoCredito(String cpf) {

    int parcelasAPagar = faker.number().numberBetween(2, 100);
    double valorCredito = faker.number().numberBetween(1, 100);
    for (int i = 0; i < faker.number().numberBetween(1, 5); i++) {
      valorCredito *= 10;
    }

    double parcela = valorCredito / parcelasAPagar;
    int parcelasRestantes = faker.bool().bool() ? 0 : faker.number().numberBetween(0, parcelasAPagar);
    double creditorestante = parcela * (parcelasAPagar - parcelasRestantes);
    boolean estaInadimplente = parcelasRestantes != 0 && faker.bool().bool();
    int mesesAtrasado = estaInadimplente ? Math.min(faker.number().numberBetween(0, 100), faker.number().numberBetween(0, 100)) : 0;

    create("historicoCredito", new String[]{"parcelasRestantes", "valorParcela", "mesesAtrasado", "estaInadimplente", "valorCreditoRestante", "valorCredito", "cpf"},
            new Object[]{parcelasRestantes, parcela, mesesAtrasado, estaInadimplente, creditorestante, valorCredito, cpf});

    return new Object[]{parcelasRestantes, parcela, mesesAtrasado, estaInadimplente, creditorestante, valorCredito, cpf};
  }

  public static Object[] criarPatrimonioAleatorio(String cpf, int nivelRenda, int saldo, int[] valoresAltosNivelRenda) {

    double investimentos = (nivelRenda < 8 ? 0 : sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda));
    double bens = sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda) + saldo;
    create("patrimonio", new String[]{"montanteInvestimentos", "montanteBens", "saldo", "cpf"},
            new Object[]{investimentos, bens, saldo, cpf});

    return new Object[]{investimentos, bens, saldo, cpf};
  }

  public static Object[] criarTransacaoAleatorio(String cpf, int saldo, int nivelRenda, int[] valoresAltosNivelRenda, LocalDate dataTransacao) {
    boolean salarioIncluso = faker.bool().bool();
    int entrada = valorMonetario(saldo);
    double saida = faker.bool().bool() ? sorteadorDeRenda(nivelRenda, valoresAltosNivelRenda) : valorMonetario(entrada);

    create("transacao", new String[]{"salarioIncluso", "dataRecorteTransacao", "valorEntrada", "valorSaida", "cpf"},
            new Object[]{salarioIncluso, dataTransacao, entrada, saida, cpf});

    return new Object[]{salarioIncluso, dataTransacao, entrada, saida, cpf};
  }

  public static Object[] criarEmpregoAleatorio(String cpf, int nivelRenda, LocalDate comecoEmprego, Boolean empregoAtual, LocalDate dataNascimento) {
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

    String[] vinculosTrabalistas = {"Estagio", "CLT", "Autonomo", "PJ"};
    String vinculoTrabalista = calculadorProbabilidade(1, vinculosTrabalistas);

    int salario = Objects.equals(vinculoTrabalista, "Estagio") ?
            faker.number().numberBetween(1528, 3500) :
            Math.max(1518, Math.max(sorteadorDeRenda(nivelRenda, valoresSalarioNivelRenda), sorteadorDeRenda(nivelRenda, valoresSalarioNivelRenda)));

    //TODO:ATUALIZAR A ALEATORIEDADE DO VINCULO TRABALISTA
    create("emprego", new String[]{"salarioEsperado", "dataInicioEmprego", "dataFinalEmprego", "idVinculoTrabalhista", "cpf"},
            new Object[]{salario, comecoEmprego, fimEmprego, 1, cpf});

    return new Object[]{salario, comecoEmprego, fimEmprego, 1, cpf};
  }

  public static Object[] criarEnderecoAleatorio(
          String cpf, LocalDate dataInicio, Boolean ultimoEndereco, String estadoUltimoEndereco) {


    String cep = faker.bool().bool() ? faker.address().postcode() : null;
    String estado = faker.bool().bool() ? faker.address().stateAbbr() : estadoUltimoEndereco;
    int numero = Math.min(faker.number().numberBetween(1, 2500), faker.number().numberBetween(1, 2500));

    LocalDate dataInicioEndereco = dataInicio.plusDays(faker.number().numberBetween(1, 60));
    LocalDate dataFimEndereco = ultimoEndereco ? null : geradorDataFim(dataInicioEndereco);


    int idEndereco = create("endereco", new String[]{"cep", "numero", "estado"}, new Object[]{cep, numero, estado});

    return
            new Object[]{idEndereco, dataInicioEndereco, dataFimEndereco, cep, numero, estado};
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
    return Math.min(faker.number().numberBetween(1, maximo), faker.number().numberBetween(1, maximo));
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

  public static int create(String tabelaNome, String[] colunaNomes, Object[] valores) {
    if (colunaNomes.length != valores.length) {
      throw new IllegalArgumentException("Column names and values must have the same length");
    }

    String colunas = String.join(", ", colunaNomes);
    String placeholders = String.join(", ", Collections.nCopies(colunaNomes.length, "?"));

    String sql = "INSERT INTO " + tabelaNome + " (" + colunas + ") VALUES (" + placeholders + ")";

    try (Connection conn = DBconexao.connect()) {
      assert conn != null;


      boolean isEnderecoTable = tabelaNome.equals("endereco");
      try (PreparedStatement pstmt = isEnderecoTable
              ? conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
              : conn.prepareStatement(sql)) {

        for (int i = 0; i < valores.length; i++) {
          pstmt.setObject(i + 1, valores[i]);
        }

        int affectedRows = pstmt.executeUpdate();

        if (affectedRows == 0) {
          throw new SQLException("Creating " + tabelaNome + " failed, no rows affected.");
        }


        if (isEnderecoTable) {
          try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
              return generatedKeys.getInt(1);
            } else {
              throw new SQLException("Creating " + tabelaNome + " failed, no ID obtained.");
            }
          }
        } else {
          return 0;
        }
      }
    } catch (SQLException e) {
      System.out.println("[ERROR] SQLException: " + e.getMessage());
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) {

    for (int i = 0; i < 30; i++) {
      test();
    }


  }
}




