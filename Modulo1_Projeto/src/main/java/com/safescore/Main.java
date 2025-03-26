package com.safescore;

/*import com.safescore.dao.CrudMethods.Read;
import com.safescore.dao.db.DBconexao;
import com.safescore.dao.CrudMethods.Update;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {

    System.out.println("<>Main<>");

//    System.out.println("\n=== Usuários ===");
//    Read.listarUsuarios();
//
//    System.out.println("\n=== Empregos ===");
//    Read.listarEmpregos();
//
//    System.out.println("\n=== Histórico de Crédito ===");
//    Read.listarHistoricoCredito();
//
//    System.out.println("\n=== Acessos ===");
//    Read.listarAcessos();
//
//    System.out.println("\n=== Patrimônios ===");
//    Read.listarPatrimonios();
//
//    System.out.println("\n=== Scores ===");
//    Read.listarScores();
//
//    System.out.println("\n=== Transações ===");
//    Read.listarTransacoes();
//
//    System.out.println("\n=== Estados Civis ===");
//    Read.listarEstadosCivis();
//
//    System.out.println("\n=== Escolaridades ===");
//    Read.listarEscolaridades();
//
//    System.out.println("\n=== Vínculos Trabalhistas ===");
//    Read.listarVinculosTrabalhistas();
//
//    System.out.println("\n=== Contratos Residenciais ===");
//    Read.listarContratosResidenciais();
//
//    System.out.println("\n=== Endereços ===");
//    Read.listarEnderecos();

    System.out.println("\n=== Dados do Usuário Específico ===");
    Read.listarDadosUsuario("00011122233");

    try (Connection conn = DBconexao.connect()) {
      if (conn != null) {
        // Testando updateUsuario
        Update.updateUsuario(conn, "000.412.583-55", "Luiz da Silva", 2, 1, 1);
        System.out.println("Update de usuario realizado com sucesso.");

//        // Testando updateEndereco
//        Update.updateEndereco(conn, 1, "12345-678", "100", "SP");
//        System.out.println("Update de endereco realizado com sucesso.");
//
//        // Testando updateEmprego
//        Update.updateEmprego(conn, 1, 5000.0, "2023-01-01", "2025-12-31", 1, "12345678900");
//        System.out.println("Update de emprego realizado com sucesso.");
//
//        // Testando updatePatrimonio
//        Update.updatePatrimonio(conn, 1, 10000.0, 50000.0, 20000.0, "12345678900");
//        System.out.println("Update de patrimonio realizado com sucesso.");
//
//        // Testando updateScore
//        Update.updateScore(conn, 1, "2025-03-21", 750, "12345678900");
//        System.out.println("Update de score realizado com sucesso.");
      } else {
        System.out.println("Falha na conexão com o banco de dados.");
      }
    } catch (SQLException e) {
      System.out.println("Erro ao executar update: " + e.getMessage());
    }
  }
}*/

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Main {
  public static void main(String[] args) {
    try {
      // Caminho do arquivo ARFF
      DataSource source = new DataSource("Modulo1_Projeto/src/main/sources/vendas.arff");
      Instances data = source.getDataSet();
      //System.out.println(data.toString());

      /*// Verificar se o dataset foi carregado corretamente
      if (data.numInstances() == 0) {
        System.out.println("Dataset carregado, mas está vazio.");
      } else {
        System.out.println("Dataset carregado com sucesso!");
        // Exibindo as instâncias carregadas
        for (int i = 0; i < data.numInstances(); i++) {
          System.out.println(data.instance(i));
        }
      }*/

      data.setClassIndex(3);

      NaiveBayes nb = new NaiveBayes();
      nb.buildClassifier(data);

      Instance novo = new DenseInstance(4);
      novo.setDataset(data);
      novo.setValue(0, "M");
      novo.setValue(1, "20-39");
      novo.setValue(2, "Nao");

      double probabilidade[] = nb.distributionForInstance(novo);
      System.out.println("Sim:" + probabilidade[1]);
      System.out.println("Não:" + probabilidade[0]);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

