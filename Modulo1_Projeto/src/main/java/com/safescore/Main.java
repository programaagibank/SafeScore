package com.safescore;

import com.safescore.sql.DBconexao;
import com.safescore.sql.CrudMethods.Update;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    System.out.println("<>Main<>");

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
}
