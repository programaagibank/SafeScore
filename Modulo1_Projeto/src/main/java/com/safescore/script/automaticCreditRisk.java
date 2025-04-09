package com.safescore.script;

import com.safescore.controller.UsuarioScoreController;
import com.safescore.dao.CrudMethods.Read;
import com.safescore.model.Usuario;
import net.datafaker.Faker;

import java.util.*;

public class automaticCreditRisk {
  static Faker faker = new Faker(Locale.of("pt", "BR"));


  public static int calcularScoreCredito(Usuario usuario) {
    double score = 999;
    int penalizacoes = 0;

    if (usuario.getRangeIdade().equals("até 25")) {
      score -= 25 * (1 + penalizacoes++ * 0.1);
    } else if (usuario.getRangeIdade().equals("41 a 60")) {
      score -= 45 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getNumeroDependentes() > 2) {
      score -= 20 * (1 + penalizacoes++ * 0.1);
    } else if (usuario.getNumeroDependentes() > 0) {
      score -= 10 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getEstadoCivil().equals("Divorciado") || usuario.getEstadoCivil().equals("Viuvo")) {
      score -= 25 * (1 + penalizacoes++ * 0.1);
    }

    String[] escolaridadeRisco = {"Ensino Fundamental Incompleto", "Sem Escolaridade"};
    String[] escolaridadeMedioRisco = {"Ensino Médio Incompleto", "Ensino Fundamental Completo"};

    if (Arrays.asList(escolaridadeRisco).contains(usuario.getEscolaridade())) {
      score -= 70 * (1 + penalizacoes++ * 0.1);
    } else if (Arrays.asList(escolaridadeMedioRisco).contains(usuario.getEscolaridade())) {
      score -= 50 * (1 + penalizacoes++ * 0.1);
    } else if (usuario.getEscolaridade().equals("Ensino Médio Completo")) {
      score -= 15 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getTempoEnderecoAnos() < 2) {
      score -= 30 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getTipoContratoResidencia().equals("Locatario") ||
            usuario.getTipoContratoResidencia().equals("Ocupante sem custo")) {
      score -= 30 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getNivelInadimplenciaEstado() > 50) {
      score -= 65 * (1 + penalizacoes++ * 0.1);
    } else if (usuario.getNivelInadimplenciaEstado() > 40) {
      score -= 35 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getTipoEmprego().equals("Autonomo") || usuario.getTipoEmprego().equals("PJ")) {
      score -= 40 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getTempoEmpregoAtual() < 2) {
      score -= 40 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getSalarioLiquidoMensal() < 3000) {
      score -= 50 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getMontanteInvestimentos() < 10000) {
      score -= 25 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getMontanteBens() < 50000) {
      score -= 40 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getSaldo() < 2000) {
      score -= 40 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getMontanteBens() > 1000000) {
      score += 50;
    }

    if (usuario.getSaldo() > 50000) {
      score += 25;
    }

    if (usuario.getRestanteMensal() < 1500) {
      score -= 50 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getValorParcelaAtiva() > (usuario.getSalarioLiquidoMensal() * 0.3)) {
      score -= 45 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getMesesAtrasado() > 0) {
      score -= 60 * (1 + penalizacoes++ * 0.1);
    }

    if (usuario.getValorCreditoRestanteTotal() > 10000) {
      score -= 30 * (1 + penalizacoes++ * 0.1);
    }

    System.out.println("Com Score de " + score);
    System.out.println();
    return (int) Math.min(faker.number().numberBetween(800, 999), Math.max(0, score));
  }

  public static Map<String, String> extrairFatores(Object[] dados) {
    Map<String, String> resultado = new HashMap<>();

    // Listas de candidatos por categoria
    List<String> positivos = new ArrayList<>();
    List<String> medios = new ArrayList<>();
    List<String> negativos = new ArrayList<>();

    // Associar nomes dos campos para controle de duplicidade
    Set<String> usados = new HashSet<>();

    // DADOS
    String rangeIdade = dados[0].toString();
    int tempoEndereco = Integer.parseInt(dados[4].toString());
    int inadimplenciaEstado = Integer.parseInt(dados[5].toString());
    double salarioLiquido = Double.parseDouble(dados[9].toString());
    double saldo = Double.parseDouble(dados[12].toString());
    double restanteMensal = Double.parseDouble(dados[13].toString());
    boolean estaInadimplente = Boolean.parseBoolean(dados[14].toString());
    double valorParcela = Double.parseDouble(dados[15].toString());
    int mesesAtrasado = Integer.parseInt(dados[16].toString());
    double creditoRestante = Double.parseDouble(dados[17].toString());

    // POSITIVOS
    if (saldo > 50000) positivos.add("Saldo alto");
    if (restanteMensal > 6000) positivos.add("Sobra mensal boa");
    if (mesesAtrasado == 0) positivos.add("Sem Atraso em creditos");
    if (tempoEndereco > 5) positivos.add("Estabilidade de Endereço");

    // MÉDIOS
    if (valorParcela > salarioLiquido * 0.3) medios.add("Parcela compromete mais de 30%");
    if (inadimplenciaEstado > 40 && inadimplenciaEstado <= 50) medios.add("Estado com inadimplência média");
    if (tempoEndereco < 2) medios.add("Pouco tempo no endereço atual");
    if (mesesAtrasado == 1) medios.add("Indicativo de Atraso em credito");


    // NEGATIVOS
    if (mesesAtrasado > 1) negativos.add("Atraso em pagamento");
    if (estaInadimplente) negativos.add("Inadimplente atualmente");
    if (creditoRestante > 10000) negativos.add("Muito crédito pendente");
    if (inadimplenciaEstado > 50) negativos.add("Estado com inadimplência alta");

    // Mistura os candidatos
    Collections.shuffle(positivos);
    Collections.shuffle(medios);
    Collections.shuffle(negativos);

    // Escolhe o primeiro de cada lista
    if (!positivos.isEmpty()) resultado.put("positivo", positivos.getFirst());
    if (!medios.isEmpty()) resultado.put("medio", medios.getFirst());
    if (!negativos.isEmpty()) resultado.put("negativo", negativos.getFirst());

    return resultado;
  }
}