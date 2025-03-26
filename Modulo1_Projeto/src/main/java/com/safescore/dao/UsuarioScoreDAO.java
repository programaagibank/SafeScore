package com.safescore.dao;

public class UsuarioScoreDAO {

  public static void buscaCaged(String cpf) {
    String querySql = "SELECT * \n" +
            "FROM contratoResidencial \n" +
            "WHERE cpf = '"+cpf+"';\n";


  }
}
