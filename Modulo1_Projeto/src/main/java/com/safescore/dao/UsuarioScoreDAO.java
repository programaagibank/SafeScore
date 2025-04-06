package com.safescore.dao;

import com.safescore.dao.CrudMethods.Read;
import com.safescore.model.Usuario;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class UsuarioScoreDAO {


  public static boolean usuarioExiste(String cpf){
    return Read.usuarioExiste(cpf);
  }

  public static Object[] estabilidadeEndereco(String cpf) {
    int tempoEnderecoAtualAnos = 0;
    int idEndereco = 0;
    int idContratoResidencial = 0;
    Date dataInicialEndereco;

    List<Object[]> contratos = Read.listarContratosResidenciais(cpf);

    for (Object[] contratoResidencial : contratos) {
      if (contratoResidencial[3] == null) {
        idEndereco = (int) contratoResidencial[5];
        idContratoResidencial = (int) contratoResidencial[4];

        dataInicialEndereco = new Date(((Date) contratoResidencial[2]).getTime());
        LocalDate dataInicialLocalDate = dataInicialEndereco.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataHoje = LocalDate.now();
        tempoEnderecoAtualAnos = (int) ChronoUnit.YEARS.between(dataInicialLocalDate, dataHoje);

        break;
      }
    }

    String estado = (String) Read.listarEnderecos(idEndereco)[2];
    int taxaCaged = getTaxasEstaduais(estado)[0];
    int taxaInadimplencia = getTaxasEstaduais(estado)[1];
    String tipoContratoResidencial = (String) Read.listarTipoContratoResidencial(idContratoResidencial)[1];
    //TODO TaxaCaged
    return new Object[]{tempoEnderecoAtualAnos, taxaInadimplencia, tipoContratoResidencial};
  }

  public static Object[] informacoesPessoais(String cpf) {
    Object[] usuario = Read.listarUsuarios(cpf);

    Date dataNascimento = new Date(((Date) usuario[2]).getTime());
    LocalDate dataNascLocal = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int idade = Period.between(dataNascLocal, LocalDate.now()).getYears();
    String rangeIdade = calcularRangeIdade(idade);

    int dependentes = (int) usuario[3];
    String escolaridade = (String) Read.listarEscolaridades((Integer) usuario[4])[1];
    String estadoCivil = (String) Read.listarEstadoCivil((Integer) usuario[5])[1];

    return new Object[]{
            rangeIdade,
            dependentes,
            estadoCivil,
            escolaridade
    };
  }

  public static Object[] empregoVinculoTrabalhista(String cpf) {
    List<Object[]> empregos = Read.listarEmpregos(cpf);

    Object[] emprego = empregos.getLast();

    double salarioEsperado = (double) emprego[1];

    java.sql.Date dataInicioEmprego = (java.sql.Date) emprego[2];
    LocalDate dataInicioEmpregoLocalDate = dataInicioEmprego.toLocalDate();

    LocalDate dataHoje = LocalDate.now();
    int tempoEmpregoAnos = (int) ChronoUnit.YEARS.between(dataInicioEmpregoLocalDate, dataHoje);

    int idVinculo = (int) emprego[4];
    Object[] vinculo = Read.listarVinculoTrabalhista(idVinculo);
    String tipoVinculo = (String) vinculo[1];

    return new Object[]{
            tipoVinculo,
            tempoEmpregoAnos,
            (int) salarioEsperado
    };
  }

  public static Object[] historicoFinanceiro(String cpf) {
    try {
      // Initialize default values
      double montanteInvestimentos = 0.0;
      double montanteBens = 0.0;
      double saldoBancario = 0.0;
      double restanteMensal = 0.0;
      boolean jaFoiInadimplente = false;
      double valorParcelasAtivas = 0.0;
      int mesesAtrasado = 0;
      double valorCreditoRestanteTotal = 0.0;

      // Handle patrimonio data
      try {
        Object[] patrimonio = Read.listarPatrimonios(cpf);
        if (patrimonio != null && patrimonio.length >= 4) {
          montanteInvestimentos = patrimonio[1] != null ? (double) patrimonio[1] : 0.0;
          montanteBens = patrimonio[2] != null ? (double) patrimonio[2] : 0.0;
          saldoBancario = patrimonio[3] != null ? (double) patrimonio[3] : 0.0;
        }
      } catch (Exception e) {
        System.err.println("⚠️ Erro ao ler patrimônios para CPF " + cpf + ": " + e.getMessage());
      }

      // Handle transacoes data
      try {
        List<Object[]> transacoes = Read.listarTransacoes(cpf);
        if (transacoes != null && !transacoes.isEmpty()) {
          Object[] ultimaTransacao = transacoes.get(transacoes.size() - 1); // Safer than getLast()
          if (ultimaTransacao != null && ultimaTransacao.length >= 4) {
            double receita = ultimaTransacao[2] != null ? (double) ultimaTransacao[2] : 0.0;
            double despesa = ultimaTransacao[3] != null ? (double) ultimaTransacao[3] : 0.0;
            restanteMensal = receita - despesa;
          }
        }
      } catch (Exception e) {
        System.err.println("⚠️ Erro ao ler transações para CPF " + cpf + ": " + e.getMessage());
      }

      // Handle creditos data
      try {
        List<Object[]> creditos = Read.listarHistoricoCredito(cpf);
        if (creditos != null && !creditos.isEmpty()) {
          for (Object[] credito : creditos) {
            if (credito != null && credito.length >= 6) {
              // Check if has been delinquent
              if (credito[4] != null && (boolean) credito[4]) {
                jaFoiInadimplente = true;
              }

              // Calculate active installments
              double valorCredito = credito[2] != null ? (double) credito[2] : 0.0;
              double valorRestante = credito[5] != null ? (double) credito[5] : 0.0;

              valorParcelasAtivas += valorRestante > 0 ? valorCredito : 0;

              // Track max months late
              int mesesAtraso = credito[3] != null ? (int) credito[3] : 0;
              mesesAtrasado = Math.max(mesesAtrasado, mesesAtraso);

              // Sum remaining credit
              valorCreditoRestanteTotal += valorRestante > 0 ? valorRestante : 0;
            }
          }
        }
      } catch (Exception e) {
        System.err.println("⚠️ Erro ao ler histórico de crédito para CPF " + cpf + ": " + e.getMessage());
      }

      return new Object[]{
              montanteInvestimentos,
              montanteBens,
              saldoBancario,
              restanteMensal,
              jaFoiInadimplente,
              valorParcelasAtivas,
              mesesAtrasado,
              valorCreditoRestanteTotal
      };

    } catch (Exception e) {
      System.err.println("⚠️ Erro crítico ao processar histórico financeiro para CPF " + cpf);
      e.printStackTrace();

      // Return safe default values in case of critical failure
      return new Object[]{
              0.0,  // montanteInvestimentos
              0.0,  // montanteBens
              0.0,  // saldoBancario
              0.0,  // restanteMensal
              false, // jaFoiInadimplente
              0.0,  // valorParcelasAtivas
              0,    // mesesAtrasado
              0.0   // valorCreditoRestanteTotal
      };
    }
  }

  private static String calcularRangeIdade(int idade) {
    if (idade <= 25) {
      return "até 25";
    } else if (idade <= 40) {
      return "26 a 40";
    } else if (idade <= 60) {
      return "41 a 60";
    } else {
      return "acima de 60";
    }
  }

  private static int[] getTaxasEstaduais(String estado) {
    Map<String, int[]> saldoPorEstado = Map.ofEntries(
            Map.entry("AC", new int[]{1183, 61}),
            Map.entry("AL", new int[]{-9589, 44}),
            Map.entry("AM", new int[]{3200, 54}),
            Map.entry("AP", new int[]{277, 62}),
            Map.entry("BA", new int[]{12482, 41}),
            Map.entry("CE", new int[]{6185, 49}),
            Map.entry("DF", new int[]{7023, 59}),
            Map.entry("ES", new int[]{6101, 41}),
            Map.entry("GO", new int[]{15742, 43}),
            Map.entry("MA", new int[]{2777, 43}),
            Map.entry("MG", new int[]{40796, 43}),
            Map.entry("MS", new int[]{4197, 54}),
            Map.entry("MT", new int[]{1085, 49}),
            Map.entry("PA", new int[]{2006, 44}),
            Map.entry("PB", new int[]{263, 41}),
            Map.entry("PE", new int[]{1364, 46}),
            Map.entry("PI", new int[]{3015, 37}),
            Map.entry("PR", new int[]{17858, 42}),
            Map.entry("RJ", new int[]{24466, 56}),
            Map.entry("RN", new int[]{1415, 47}),
            Map.entry("RO", new int[]{1395, 48}),
            Map.entry("RR", new int[]{632, 50}),
            Map.entry("RS", new int[]{10490, 41}),
            Map.entry("SC", new int[]{13892, 36}),
            Map.entry("SE", new int[]{-1875, 44}),
            Map.entry("SP", new int[]{76941, 49}),
            Map.entry("TO", new int[]{977, 51})
    );

    return saldoPorEstado.getOrDefault(estado.toUpperCase(), new int[]{0, 0});
  }
}
