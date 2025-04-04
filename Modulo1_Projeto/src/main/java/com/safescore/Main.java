package com.safescore;

import com.safescore.dao.CrudMethods.Read;
import com.safescore.dao.db.DBconexao;
import com.safescore.dao.CrudMethods.Update;
import com.safescore.service.IndicadoresService;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {

    System.out.println("<>Main<>");


    try (Connection conn = DBconexao.connect()) {

    } catch (SQLException e) {
      System.out.println("Erro ao executar update: " + e.getMessage());
    }
  }
}
