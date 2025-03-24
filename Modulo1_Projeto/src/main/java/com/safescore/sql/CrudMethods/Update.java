package com.safescore.sql.CrudMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {

    public static void updateUsuario(Connection conn, String cpf, String nome, int dependentes, int idEscolaridade, int idEstadoCivil) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, dependentes = ?, idEscolaridade = ?, idEstadoCivil = ? WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, dependentes);
            stmt.setInt(3, idEscolaridade);
            stmt.setInt(4, idEstadoCivil);
            stmt.setString(5, cpf);
            stmt.executeUpdate();
        }
    }

    public static void updateEndereco(Connection conn, int idEndereco, String cep, String numero, String estado) throws SQLException {
        String sql = "UPDATE endereco SET cep = ?, numero = ?, estado = ? WHERE idEndereco = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cep);
            stmt.setString(2, numero);
            stmt.setString(3, estado);
            stmt.setInt(4, idEndereco);
            stmt.executeUpdate();
        }
    }

    public static void updateEmprego(Connection conn, int idEmprego, double salarioEsperado, String dataInicio, String dataFinal, int idVinculoTrabalhista, String cpf) throws SQLException {
        String sql = "UPDATE emprego SET salarioEsperado = ?, dataInicioEmprego = ?, dataFinalEmprego = ?, idVinculoTrabalhista = ? WHERE idEmprego = ? AND cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, salarioEsperado);
            stmt.setString(2, dataInicio);
            stmt.setString(3, dataFinal);
            stmt.setInt(4, idVinculoTrabalhista);
            stmt.setInt(5, idEmprego);
            stmt.setString(6, cpf);
            stmt.executeUpdate();
        }
    }

    public static void updatePatrimonio(Connection conn, int idPatrimonio, double montanteInvestimentos, double montanteBens, double saldo, String cpf) throws SQLException {
        String sql = "UPDATE patrimonio SET montanteInvestimentos = ?, montanteBens = ?, saldo = ? WHERE idPatrimonio = ? AND cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, montanteInvestimentos);
            stmt.setDouble(2, montanteBens);
            stmt.setDouble(3, saldo);
            stmt.setInt(4, idPatrimonio);
            stmt.setString(5, cpf);
            stmt.executeUpdate();
        }
    }

    public static void updateScore(Connection conn, int idScore, String dataCalculo, int score, String cpf) throws SQLException {
        String sql = "UPDATE score SET dataCalculo = ?, score = ? WHERE idScore = ? AND cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dataCalculo);
            stmt.setInt(2, score);
            stmt.setInt(3, idScore);
            stmt.setString(4, cpf);
            stmt.executeUpdate();
        }
    }
}
