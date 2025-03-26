package com.safescore.dao.CrudMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import com.safescore.dao.db.DBconexao;

public class Create {

    // Método para registrar um acesso e calcular o score do usuário
    public static void registrarAcessoECalcularScore(String cpf) {
        try (Connection conn = DBconexao.connect()) {
            // Registrar acesso na tabela 'acessos'
            String sqlInserirAcesso = "INSERT INTO acessos (cpf, dataHoraAcesso) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlInserirAcesso)) {
                stmt.setString(1, cpf);
                stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis())); // Data atual
                stmt.executeUpdate();
                System.out.println("Acesso registrado com sucesso para o CPF: " + cpf);
            }

            // Atualizar número de acessos na tabela 'usuario'
            String sqlContarAcessos = "SELECT COUNT(*) FROM acessos WHERE cpf = ?";
            int numeroAcessos = 0;

            try (PreparedStatement stmt = conn.prepareStatement(sqlContarAcessos)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    numeroAcessos = rs.getInt(1);
                }
            }

            String sqlAtualizarAcessos = "UPDATE usuario SET numeroAcessos = ? WHERE cpf = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlAtualizarAcessos)) {
                stmt.setInt(1, numeroAcessos);
                stmt.setString(2, cpf);
                stmt.executeUpdate();
                System.out.println("Número de acessos atualizado: " + numeroAcessos);
            }

            // Calcular e inserir um novo score para o usuário
            int scoreCalculado = calcularScore(cpf, conn);

            String sqlInserirScore = "INSERT INTO score (cpf, dataCalculo, score) VALUES (?, NOW(), ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlInserirScore)) {
                stmt.setString(1, cpf);
                stmt.setInt(2, scoreCalculado);
                stmt.executeUpdate();
                System.out.println("Score calculado e inserido: " + scoreCalculado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para calcular o score do usuário com base nas informações disponíveis
    private static int calcularScore(String cpf, Connection conn) {
        int score = 500; // Score inicial padrão

        try {
            // Verificar histórico de crédito
            String sqlCredito = "SELECT mesesAtrasado, estaInadimplente FROM historicoCredito WHERE cpf = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlCredito)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int mesesAtrasado = rs.getInt("mesesAtrasado");
                    boolean inadimplente = rs.getBoolean("estaInadimplente");

                    if (inadimplente) {
                        score -= 200; // Penalização para inadimplentes
                    } else if (mesesAtrasado > 0) {
                        score -= mesesAtrasado * 10; // Penalização por atraso
                    }
                }
            }

            // Verificar histórico de emprego
            String sqlEmprego = "SELECT salarioEsperado FROM emprego WHERE cpf = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlEmprego)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    double salario = rs.getDouble("salarioEsperado");
                    if (salario > 5000) {
                        score += 50; // Bônus para salários altos
                    }
                }
            }

            // Verificar patrimônio
            String sqlPatrimonio = "SELECT saldo FROM patrimonio WHERE cpf = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlPatrimonio)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    double saldo = rs.getDouble("saldo");
                    if (saldo > 10000) {
                        score += 100; // Bônus para saldo alto
                    }
                }
            }

            // Garantir que o score esteja dentro do limite (0 - 1000)
            if (score < 0) score = 0;
            if (score > 1000) score = 1000;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return score;
    }
}
