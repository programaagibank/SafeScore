package com.safescore.dao;

import com.safescore.dao.CrudMethods.Read;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UsuarioScoreDAO {


  public static void main(String[] args) {
    estabilidadeEndereco("418.463.818-00");
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
        tempoEnderecoAtualDias = (int) ChronoUnit.DAYS.between(dataInicialLocalDate, dataHoje);

        break;
      }
    }

    String estado = (String) Read.listarEnderecos(idEndereco)[2];
    int taxaCaged = getTaxaCaged(estado);
    int totalEnderecos = contratos.size();
    String tipoContratoResidencial = (String) Read.listarTipoContratoResidencial(idContratoResidencial)[1];

    return new Object[]{totalEnderecos, tempoEnderecoAtualDias, taxaCaged, tipoContratoResidencial};
  }

  private static int getTaxaCaged(String estado) {
    Map<String, Integer> saldoPorEstado = Map.ofEntries(
            Map.entry("AC", 1183), Map.entry("AL", -9589), Map.entry("AM", 3200),
            Map.entry("AP", 277), Map.entry("BA", 12482), Map.entry("CE", 6185),
            Map.entry("DF", 7023), Map.entry("ES", 6101), Map.entry("GO", 15742),
            Map.entry("MA", 2777), Map.entry("MG", 40796), Map.entry("MS", 4197),
            Map.entry("MT", 1085), Map.entry("PA", 2006), Map.entry("PB", 263),
            Map.entry("PE", 1364), Map.entry("PI", 3015), Map.entry("PR", 17858),
            Map.entry("RJ", 24466), Map.entry("RN", 1415), Map.entry("RO", 1395),
            Map.entry("RR", 632), Map.entry("RS", 10490), Map.entry("SC", 13892),
            Map.entry("SE", -1875), Map.entry("SP", 76941), Map.entry("TO", 977)
    );

    int taxaCaged = saldoPorEstado.get(estado);
    return taxaCaged;
  }
}
