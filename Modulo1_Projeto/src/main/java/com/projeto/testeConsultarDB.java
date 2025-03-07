package com.projeto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testeConsultarDB {
    public static void listarUsuarios() {
        String sql = "SELECT * FROM usuario";  // Pegando todos os usuários

        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("👤 Lista de usuarios:");
            while (rs.next()) {
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("dataNascimento");
                int dependentes = rs.getInt("dependentes");
                int idEscolaridade = rs.getInt("idEscolaridade");
                int idEstadoCivil = rs.getInt("idEstadoCivil");

                System.out.println("CPF: " + cpf + " | Nome: " + nome +
                        " | Nascimento: " + dataNascimento +
                        " | Dependentes: " + dependentes +
                        " | Escolaridade ID: " + idEscolaridade +
                        " | Estado Civil ID: " + idEstadoCivil);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuarios: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        listarUsuarios();
    }
}
