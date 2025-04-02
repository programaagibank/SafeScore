package com.safescore.script;

import java.util.Arrays;

public class automaticCreditRisk {
  private static final int LIMITE_RISCO = 25;

  public static void main(String[] args) {
    UsuarioStub usuarioSeguro = new UsuarioStub(
            "26 a 40",
            1,
            "Casado",
            "Superior Completo",
            5,
            "Baixo",
            3.8,
            "Proprietario",
            9.0,
            "CLT",
            4,
            8500.0,
            50000.0,
            250000.0,
            15000.0,
            4500.0,
            false,
            1200.0,
            0,
            8000.0
    );

    UsuarioStub usuarioRisco = new UsuarioStub(
            "acima de 60",
            4,
            "Divorciado",
            "Ensino Fundamental Incompleto",
            1,
            "Alto",
            1.8,
            "Ocupante sem custo",
            12.5,
            "Autonomo",
            1,
            1800.0,
            2000.0,
            15000.0,
            500.0,
            800.0,
            true,
            750.0,
            3,
            25000.0
    );

    boolean risco1 = calcularRiscoCredito(usuarioSeguro);
    boolean risco2 = calcularRiscoCredito(usuarioRisco);

    System.out.println("\nUsuário 1 - Risco de inadimplência: " + risco1);
    System.out.println("Usuário 2 - Risco de inadimplência: " + risco2);
  }
  public static boolean calcularRiscoCredito(UsuarioStub usuario) {
    int contadorRisco = 0;

    if (usuario.rangeIdade().equals("até 25") || usuario.rangeIdade().equals("acima de 60")) {
      contadorRisco += 2;
    } else if (usuario.rangeIdade().equals("41 a 60")) {
      contadorRisco += 1;
    }

    if (usuario.numeroDependentes() > 2) contadorRisco += 2;
    else if (usuario.numeroDependentes() > 0) contadorRisco += 1;

    if (usuario.estadoCivil().equals("Divorciado") || usuario.estadoCivil().equals("Viuvo")) {
      contadorRisco += 1;
    }

    String[] escolaridadeRisco = {"Ensino Fundamental Incompleto", "Sem Escolaridade"};
    String[] escolaridadeMedioRisco = {"Ensino Médio Incompleto", "Ensino Fundamental Completo"};
    if (Arrays.asList(escolaridadeRisco).contains(usuario.escolaridade())) contadorRisco += 3;
    else if (Arrays.asList(escolaridadeMedioRisco).contains(usuario.escolaridade())) contadorRisco += 2;
    else if (usuario.escolaridade().equals("Ensino Médio Completo")) contadorRisco += 1;

    if (usuario.tempoEnderecoAnos() < 2) contadorRisco += 2;
    if (usuario.tipoContratoResidencia().equals("Locatario") ||
            usuario.tipoContratoResidencia().equals("Ocupante sem custo")) {
      contadorRisco += 2;
    }

    if (usuario.nivelInadimplenciaEstado().equals("Alto")) contadorRisco += 3;
    else if (usuario.nivelInadimplenciaEstado().equals("Medio")) contadorRisco += 1;

    if (usuario.taxaCAGED() < 2.5) contadorRisco += 2;
    if (usuario.taxaSELIC() > 10.0) contadorRisco += 1;

    if (usuario.tipoEmprego().equals("Autonomo") || usuario.tipoEmprego().equals("PJ")) {
      contadorRisco += 2;
    }
    if (usuario.tempoEmpregoAtual() < 2) contadorRisco += 2;

    if (usuario.salarioLiquidoMensal() < 3000) contadorRisco += 2;
    if (usuario.montanteInvestimentos() < 10000) contadorRisco += 1;
    if (usuario.montanteBens() < 50000) contadorRisco += 2;
    if (usuario.saldo() < 2000) contadorRisco += 2;
    if (usuario.restanteMensal() < 1500) contadorRisco += 3;

    if (usuario.estaInadimplente()) contadorRisco += 10;
    if (usuario.valorParcela() > (usuario.salarioLiquidoMensal() * 0.3)) contadorRisco += 3;
    if (usuario.mesesAtrasado() > 0) contadorRisco += 4;
    if (usuario.valorCreditoRestante() > 10000) contadorRisco += 2;

    System.out.println("Pontuação total de risco: " + contadorRisco);
    return contadorRisco >= LIMITE_RISCO;
  }

  record UsuarioStub(
          String rangeIdade,
          int numeroDependentes,
          String estadoCivil,
          String escolaridade,
          int tempoEnderecoAnos,
          String nivelInadimplenciaEstado,
          double taxaCAGED,
          String tipoContratoResidencia,
          double taxaSELIC,
          String tipoEmprego,
          int tempoEmpregoAtual,
          double salarioLiquidoMensal,
          double montanteInvestimentos,
          double montanteBens,
          double saldo,
          double restanteMensal,
          boolean estaInadimplente,
          double valorParcela,
          int mesesAtrasado,
          double valorCreditoRestante
  ) {}
}