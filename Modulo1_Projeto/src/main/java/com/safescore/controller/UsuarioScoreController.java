package com.safescore.controller;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.safescore.dao.db.DBconexao;

public class UsuarioScoreController {
    private Classifier model;
    private Instances trainingData;

    // Inicializa e treina o modelo
    public void treinarModelo(String arffPath) throws Exception {
        System.out.println("Tentando carregar o ARFF: " + arffPath);
        DataSource source = new DataSource(arffPath);
        trainingData = source.getDataSet();
        trainingData.setClassIndex(trainingData.numAttributes() - 1); // Última coluna é a classe (score)

        model = new J48(); // Árvore de decisão
        model.buildClassifier(trainingData);
        System.out.println("[✓] Modelo treinado com sucesso!");
    }

    // Extrai características do banco e retorna um vetor de atributos
    public Instance extrairAtributosDeUsuario(String cpf) throws Exception {
        List<Double> atributos = new ArrayList<>();

        try (Connection conn = DBconexao.connect()) {
            if (conn == null) throw new SQLException("Conexão falhou.");

            // Exemplo: dados da tabela patrimonio
            PreparedStatement pstmt = conn.prepareStatement("SELECT saldo, montanteInvestimentos, montanteBens FROM patrimonio WHERE cpf = ?");
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                atributos.add(rs.getDouble("saldo"));
                atributos.add(rs.getDouble("montanteInvestimentos"));
                atributos.add(rs.getDouble("montanteBens"));
            } else {
                atributos.add(0.0); atributos.add(0.0); atributos.add(0.0);
            }

            // Outros atributos simplificados:
            pstmt = conn.prepareStatement("SELECT AVG(valorParcela), SUM(valorCreditoRestante), MAX(mesesAtrasado) FROM historicoCredito WHERE cpf = ?");
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                atributos.add(rs.getDouble(1)); // Média valorParcela
                atributos.add(rs.getDouble(2)); // Soma valorCreditoRestante
                atributos.add((double) rs.getInt(3));// Máximo de mesesAtrasado
            } else {
                atributos.add(0.0); atributos.add(0.0); atributos.add(0.0);
            }

            // Transações
            pstmt = conn.prepareStatement("SELECT AVG(valorEntrada), AVG(valorSaida) FROM transacao WHERE cpf = ?");
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                atributos.add(rs.getDouble(1));
                atributos.add(rs.getDouble(2));
            } else {
                atributos.add(0.0); atributos.add(0.0);
            }

            // Emprego
            pstmt = conn.prepareStatement("SELECT MAX(salarioEsperado) FROM emprego WHERE cpf = ?");
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            atributos.add(rs.next() ? rs.getDouble(1) : 0.0);
        }

        // Cria um objeto Instance com os dados
        double[] vals = atributos.stream().mapToDouble(Double::doubleValue).toArray();
        Instance novo = new DenseInstance(1.0, vals);
        novo.setDataset(trainingData); // precisa apontar para o dataset com metadados
        return novo;
    }

    public double preverScore(Instance instancia) throws Exception {
        return model.classifyInstance(instancia);
    }
}
