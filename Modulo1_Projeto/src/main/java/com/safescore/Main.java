package com.safescore.sql.CrudMethods;

import com.safescore.sql.CrudMethods.Read;

public class Main {
  public static void main(String[] args) {

    System.out.println("<>Main<>");

    System.out.println("Usuários:");
    Read.listarUsuarios();

    System.out.println("\nEmpregos:");
    Read.listarEmpregos();

    System.out.println("\nHistórico de Crédito:");
    Read.listarHistoricoCredito();
  }
}
