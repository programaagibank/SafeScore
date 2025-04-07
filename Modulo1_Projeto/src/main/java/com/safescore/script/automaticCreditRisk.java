package com.safescore.script;

import com.safescore.controller.UsuarioScoreController;
import com.safescore.dao.CrudMethods.Read;
import com.safescore.model.Usuario;
import net.datafaker.Faker;

import java.util.Arrays;
import java.util.Locale;

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


}