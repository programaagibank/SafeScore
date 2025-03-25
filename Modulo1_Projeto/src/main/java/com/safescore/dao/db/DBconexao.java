package com.safescore.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconexao {
    private static final String URL = "jdbc:mysql://mysql-safescoredb-safescoredb.g.aivencloud.com:19296/defaultdb?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS__SmEXSEJN4bIYLjqL_Y";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexao bem-sucedida!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro na conexao: " + e.getMessage());
            return null;
        }
    }
}

