package com.safescore;

public class Main {
  public static void main(String[] args) {

    System.out.println("<>Main<>");

    System.out.println("Usuários:");
    ReadData.listarUsuarios();

    System.out.println("\nEmpregos:");
    ReadData.listarEmpregos();

    System.out.println("\nHistórico de Crédito:");
    ReadData.listarHistoricoCredito();
  }
}
