package com.safescore.script;

import com.safescore.controller.UsuarioScoreController;
import com.safescore.dao.CrudMethods.Read;
import com.safescore.model.Usuario;

import java.util.Arrays;

public class automaticCreditRisk {
  private static final int LIMITE_RISCO = 25;

  public static void main(String[] args) {
    String[] usuariosCpfs = Read.listarCpfs();
    for (String usuarioCPF : usuariosCpfs){
      Usuario usuario = UsuarioScoreController.definirUsuario(usuarioCPF);

    }
  }

  public static boolean calcularRiscoCredito(Usuario usuario) {
    int contadorRisco = 0;

    if (usuario.getRangeIdade().equals("até 25") || usuario.getRangeIdade().equals("acima de 60")) {
      contadorRisco += 2;
    } else if (usuario.getRangeIdade().equals("41 a 60")) {
      contadorRisco += 1;
    }

    if (usuario.getNumeroDependentes() > 2) contadorRisco += 2;
    else if (usuario.getNumeroDependentes() > 0) contadorRisco += 1;

    if (usuario.getEstadoCivil().equals("Divorciado") || usuario.getEstadoCivil().equals("Viuvo")) {
      contadorRisco += 1;
    }

    String[] escolaridadeRisco = {"Ensino Fundamental Incompleto", "Sem Escolaridade"};
    String[] escolaridadeMedioRisco = {"Ensino Médio Incompleto", "Ensino Fundamental Completo"};
    if (Arrays.asList(escolaridadeRisco).contains(usuario.getEscolaridade())) contadorRisco += 3;
    else if (Arrays.asList(escolaridadeMedioRisco).contains(usuario.getEscolaridade())) contadorRisco += 2;
    else if (usuario.getEscolaridade().equals("Ensino Médio Completo")) contadorRisco += 1;

    if (usuario.getTempoEnderecoAnos() < 2) contadorRisco += 2;
    if (usuario.getTipoContratoResidencia().equals("Locatario") ||
            usuario.getTipoContratoResidencia().equals("Ocupante sem custo")) {
      contadorRisco += 2;
    }

    if (usuario.getNivelInadimplenciaEstado() > 50) contadorRisco += 3;
    else if (usuario.getNivelInadimplenciaEstado() > 40) contadorRisco += 1;

//    if (usuario.taxaCAGED() < 2.5) contadorRisco += 2;
//    if (usuario.taxaSELIC() > 10.0) contadorRisco += 1;

    if (usuario.getTipoEmprego().equals("Autonomo") || usuario.getTipoEmprego().equals("PJ")) {
      contadorRisco += 2;
    }
    if (usuario.getTempoEmpregoAtual() < 2) contadorRisco += 2;

    if (usuario.getSalarioLiquidoMensal() < 3000) contadorRisco += 2;
    if (usuario.getMontanteInvestimentos() < 10000) contadorRisco += 1;
    if (usuario.getMontanteBens() < 50000) contadorRisco += 2;
    if (usuario.getSaldo() < 2000) contadorRisco += 2;
    if (usuario.getRestanteMensal() < 1500) contadorRisco += 3;

//    if (usuario.estaInadimplente()) contadorRisco += 10;
    if (usuario.getValorParcelaAtiva() > (usuario.getSalarioLiquidoMensal() * 0.3)) contadorRisco += 3;
    if (usuario.getMesesAtrasado() > 0) contadorRisco += 4;
    if (usuario.getValorCreditoRestanteTotal() > 10000) contadorRisco += 2;

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
//          double taxaCAGED,
          String tipoContratoResidencia,
//          double taxaSELIC,
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