package com.projeto;

import java.sql.*;

public class testeConsultarTipoDB {
    public static void listarUsuariosComTipos() {
        String sql = "SELECT * FROM usuario";

        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // metadados das colunas
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("Tipos das colunas da tabela usuario:");

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnType = metaData.getColumnTypeName(i);

                System.out.println(columnName + " -> " + columnType);
            }

        } catch (SQLException e) {
            System.out.println("Errooou" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        listarUsuariosComTipos();
    }
}
