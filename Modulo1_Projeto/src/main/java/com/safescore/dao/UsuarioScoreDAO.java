package com.safescore.dao;

import com.safescore.dao.CrudMethods.Read;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UsuarioScoreDAO {


  public static void main(String[] args) {
    estabilidadeEndereco("418.463.818-00");
    informacoesPessoais("418.463.818-00");
  }

  public static Object[] estabilidadeEndereco(String cpf) {
    int tempoEnderecoAtualDias = 0;
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
        tempoEnderecoAtualDias = (int) ChronoUnit.YEARS.between(dataInicialLocalDate, dataHoje);

        break;
      }
    }

    String estado = (String) Read.listarEnderecos(idEndereco)[2];
    int taxaCaged = getTaxasEstaduais(estado)[0];
    int taxaInadimplencia = getTaxasEstaduais(estado)[1];
    String tipoContratoResidencial = (String) Read.listarTipoContratoResidencial(idContratoResidencial)[1];

    return new Object[]{tempoEnderecoAtualDias, tipoContratoResidencial, taxaCaged, taxaInadimplencia};
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
            escolaridade,
            estadoCivil
    };
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
