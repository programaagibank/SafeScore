package com.safescore.script;

import com.safescore.dao.db.DBconexao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;

public class ArffExporter {

    public static void exportarParaArff(String caminhoArff) {
        String query = """
            SELECT 
                p.saldo,
                p.montanteInvestimentos,
                p.montanteBens,
                AVG(h.valorParcela) AS mediaParcela,
                SUM(h.valorCreditoRestante) AS totalCreditoRestante,
                MAX(h.mesesAtrasado) AS maxMesesAtrasado,
                AVG(t.valorEntrada) AS mediaEntrada,
                AVG(t.valorSaida) AS mediaSaida,
                MAX(e.salarioEsperado) AS salario,
                MAX(s.score) AS score
            FROM usuario u
            LEFT JOIN patrimonio p ON u.cpf = p.cpf
            LEFT JOIN historicoCredito h ON u.cpf = h.cpf
            LEFT JOIN transacao t ON u.cpf = t.cpf
            LEFT JOIN emprego e ON u.cpf = e.cpf
            LEFT JOIN score s ON u.cpf = s.cpf
            GROUP BY u.cpf
        """;

        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             PrintWriter writer = new PrintWriter(new FileWriter(caminhoArff))) {

            // Escreve cabeçalho ARFF
            writer.println("@relation risco_credito");
            writer.println();
            writer.println("@attribute saldo numeric");
            writer.println("@attribute investimentos numeric");
            writer.println("@attribute bens numeric");
            writer.println("@attribute media_parcela numeric");
            writer.println("@attribute credito_restante numeric");
            writer.println("@attribute meses_atrasado numeric");
            writer.println("@attribute entrada_media numeric");
            writer.println("@attribute saida_media numeric");
            writer.println("@attribute salario numeric");
            writer.println("@attribute score numeric");
            writer.println();
            writer.println("@data");

            while (rs.next()) {
                String linha = String.format("%f,%f,%f,%f,%f,%f,%f,%f,%f,%f",
                        rs.getDouble("saldo"),
                        rs.getDouble("montanteInvestimentos"),
                        rs.getDouble("montanteBens"),
                        rs.getDouble("mediaParcela"),
                        rs.getDouble("totalCreditoRestante"),
                        rs.getDouble("maxMesesAtrasado"),
                        rs.getDouble("mediaEntrada"),
                        rs.getDouble("mediaSaida"),
                        rs.getDouble("salario"),
                        rs.getDouble("score"));
                writer.println(linha);
            }

            System.out.println("✅ Arquivo ARFF gerado com sucesso: " + caminhoArff);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        exportarParaArff("Modulo1_Projeto/src/main/sources/usuario.arff");
    }
}
