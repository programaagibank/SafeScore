package com.safescore;

import com.safescore.sql.CrudMethods.Read;

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

  }
}
