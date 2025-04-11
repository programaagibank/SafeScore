package com.safescore.script;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Locale; // <-- IMPORTANTE

import com.safescore.controller.UsuarioScoreController;
import com.safescore.dao.CrudMethods.Read;
import com.safescore.model.Usuario;

public class ArffExporter {

  private static boolean isFileInitialized(String caminhoArff) {
    File file = new File(caminhoArff);
    return file.exists() && file.length() > 0;
  }

  public static void initializeArffFile(String caminhoArff) {
    if (isFileInitialized(caminhoArff)) {
      System.out.println("🟡 Arquivo ARFF já contém dados, pulando escrita de atributos: " + caminhoArff);
      return;
    }

    try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArff))) {
      writeAttributes(writer);
      System.out.println("✅ Arquivo ARFF inicializado com atributos: " + caminhoArff);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void writeAttributes(PrintWriter writer) {
    writer.println("@relation risco_credito");
    writer.println();
    writer.println("@attribute rangeIdade string");
    writer.println("@attribute numeroDependentes numeric");
    writer.println("@attribute estadoCivil string");
    writer.println("@attribute escolaridade string");
    writer.println("@attribute tempoEnderecoAnos numeric");
    writer.println("@attribute nivelInadimplenciaEstado numeric");
    writer.println("@attribute tipoContratoResidencia string");
    writer.println("@attribute tipoEmprego string");
    writer.println("@attribute tempoEmpregoAtual numeric");
    writer.println("@attribute salarioLiquidoMensal numeric");
    writer.println("@attribute montanteInvestimentos numeric");
    writer.println("@attribute ontanteBens numeric");
    writer.println("@attribute saldo numeric");
    writer.println("@attribute restanteMensal numeric");
    writer.println("@attribute estaInadimplente {0,1}");
    writer.println("@attribute valorParcelaAtiva numeric");
    writer.println("@attribute mesesAtrasado numeric");
    writer.println("@attribute valorCreditoRestanteTotal numeric");
    writer.println("@attribute score numeric");
    writer.println();
    writer.println("@data");
  }

  public static void appendUserToArff(String caminhoArff, Usuario usuario) {
    int score = automaticCreditRisk.calcularScoreCredito(usuario);

    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(caminhoArff, true)))) {
      writeDataLine(writer, usuario, score);
      System.out.println("✓ Usuário " + usuario.getCpf() + " adicionado ao arquivo");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void writeDataLine(PrintWriter writer, Usuario usuario, boolean seraInadimplente) {
    String linha = String.format(Locale.US, "'%s',%d,'%s','%s',%d,%d,'%s','%s',%d,%.2f,%.2f,%.2f,%.2f,%.2f,%d,%.2f,%d,%.2f,%d",
            usuario.getRangeIdade(),
            usuario.getNumeroDependentes(),
            usuario.getEstadoCivil(),
            usuario.getEscolaridade(),
            usuario.getTempoEnderecoAnos(),
            usuario.getNivelInadimplenciaEstado(),
            usuario.getTipoContratoResidencia(),
            usuario.getTipoEmprego(),
            usuario.getTempoEmpregoAtual(),
            usuario.getSalarioLiquidoMensal(),
            usuario.getMontanteInvestimentos(),
            usuario.getMontanteBens(),
            usuario.getSaldo(),
            usuario.getRestanteMensal(),
            usuario.isEstaInadimplente() ? 1 : 0,
            usuario.getValorParcelaAtiva(),
            usuario.getMesesAtrasado(),
            usuario.getValorCreditoRestanteTotal(),
            seraInadimplente ? 1 : 0);
    writer.println(linha);
  }

  private static void writeDataLine(PrintWriter writer, Usuario usuario, int score) {
    String linha = String.format(Locale.US, "'%s',%d,'%s','%s',%d,%d,'%s','%s',%d,%.2f,%.2f,%.2f,%.2f,%.2f,%d,%.2f,%d,%.2f,%d",
            usuario.getRangeIdade(),
            usuario.getNumeroDependentes(),
            usuario.getEstadoCivil(),
            usuario.getEscolaridade(),
            usuario.getTempoEnderecoAnos(),
            usuario.getNivelInadimplenciaEstado(),
            usuario.getTipoContratoResidencia(),
            usuario.getTipoEmprego(),
            usuario.getTempoEmpregoAtual(),
            usuario.getSalarioLiquidoMensal(),
            usuario.getMontanteInvestimentos(),
            usuario.getMontanteBens(),
            usuario.getSaldo(),
            usuario.getRestanteMensal(),
            usuario.isEstaInadimplente() ? 1 : 0,
            usuario.getValorParcelaAtiva(),
            usuario.getMesesAtrasado(),
            usuario.getValorCreditoRestanteTotal(),
            score);
    writer.println(linha);
  }

  public static void exportAllUsersToArff(String caminhoArff) {
    initializeArffFile(caminhoArff);

    String[] usuariosCpfs = Read.listarCpfs();
    for (String usuarioCPF : usuariosCpfs) {
      Usuario usuario = UsuarioScoreController.definirUsuario(usuarioCPF);
      appendUserToArff(caminhoArff, usuario);
    }
  }

  public static void main(String[] args) {
    exportAllUsersToArff("src/main/sources/usuarios_angelo.arff");
  }
}
