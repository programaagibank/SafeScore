package com.safescore.sql.CrudMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.safescore.sql.DBconexao;

public class Delete {
    public static void deletarUsuario(String cpf) {
        String sql = "DELETE FROM usuario WHERE cpf = ?";

        try (Connection conn = DBconexao.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário deletado com sucesso.");
            } else {
                System.out.println("Nenhum usuário encontrado com esse CPF.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}