package com.safescore.script;

import com.safescore.dao.db.DBconexao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import com.safescore.model.Usuario;


public class ArffExporter {

    public static void exportarParaArff(String caminhoArff, Usuario usuarioAtributos) {
      try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArff))) {

        // Escreve cabeçalho ARFF
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
        writer.println("@attribute montanteBens numeric");
        writer.println("@attribute saldo numeric");
        writer.println("@attribute restanteMensal numeric");
        writer.println("@attribute estaInadimplente {0,1}");
        writer.println("@attribute valorParcelaAtiva numeric");
        writer.println("@attribute mesesAtrasado numeric");
        writer.println("@attribute valorCreditoRestanteTotal numeric");
        writer.println("@attribute seraInadimplente {0,1}");
        writer.println();
        writer.println("@data");

            boolean seraInadimplente = automaticCreditRisk.calcularRiscoCredito(usuarioAtributos);

        String linha = String.format("'%s',%d,'%s','%s',%d,%d,'%s','%s',%d,%.2f,%.2f,%.2f,%.2f,%.2f,%d,%.2f,%d,%.2f,%d",
                usuarioAtributos.getRangeIdade(),
                usuarioAtributos.getNumeroDependentes(),
                usuarioAtributos.getEstadoCivil(),
                usuarioAtributos.getEscolaridade(),
                usuarioAtributos.getTempoEnderecoAnos(),
                usuarioAtributos.getNivelInadimplenciaEstado(),
                usuarioAtributos.getTipoContratoResidencia(),
                usuarioAtributos.getTipoEmprego(),
                usuarioAtributos.getTempoEmpregoAtual(),
                usuarioAtributos.getSalarioLiquidoMensal(),
                usuarioAtributos.getMontanteInvestimentos(),
                usuarioAtributos.getMontanteBens(),
                usuarioAtributos.getSaldo(),
                usuarioAtributos.getRestanteMensal(),
                usuarioAtributos.isEstaInadimplente() ? 1 : 0,
                usuarioAtributos.getValorParcelaAtiva(),
                usuarioAtributos.getMesesAtrasado(),
                usuarioAtributos.getValorCreditoRestanteTotal(),
                seraInadimplente ? 1 : 0);
        writer.println(linha);

            System.out.println("✅ Arquivo ARFF gerado com sucesso: " + caminhoArff);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        exportarParaArff("Modulo1_Projeto/src/main/sources/usuario.arff");
    }
}
