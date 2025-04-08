package com.safescore.controller;

import com.safescore.model.Usuario;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Evaluation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;

public class WekaController {
  private Classifier model;
  private Instances trainingData;

  // Inicializa e treina o modelo
  public void treinarModelo(String caminho) throws Exception {

    DataSource source = new DataSource(caminho);
    trainingData = source.getDataSet();
    trainingData.setClassIndex(trainingData.numAttributes() - 1);

    model = new LinearRegression();
    model.buildClassifier(trainingData);

    System.out.println("[✓] Modelo treinado com sucesso!");
  }


  // Extrai características do banco e retorna um vetor de atributos
  public void avaliarModelo() {
    try {
      if (trainingData == null || model == null) {
        System.out.println("⚠️ Modelo não treinado ainda.");
        return;
      }

      Evaluation avaliacao = new Evaluation(trainingData);
      int folds = Math.min(10, trainingData.numInstances());
      avaliacao.crossValidateModel(model, trainingData, folds, new Random(1));

      System.out.println("\n🔍 === Avaliação do Modelo (Regressão) ===");
      System.out.printf("📈 Erro Médio Absoluto: %.2f\n", avaliacao.meanAbsoluteError());
      System.out.printf("📉 Erro Quadrático Médio: %.2f\n", avaliacao.rootMeanSquaredError());
      System.out.printf("🔗 Correlação com valor real: %.2f\n", avaliacao.correlationCoefficient());
      System.out.println("\n📋 Resumo:");
      System.out.println(avaliacao.toSummaryString());

    } catch (Exception e) {
      System.out.println("❌ Erro durante avaliação: " + e.getMessage());
      e.printStackTrace();
    }
  }


  public Instance converterUsuarioParaInstance(Usuario usuario, Instances datasetTemplate) {
    double[] valores = new double[datasetTemplate.numAttributes()];

    valores[0] = datasetTemplate.attribute(0).indexOfValue(usuario.getRangeIdade());
    valores[1] = usuario.getNumeroDependentes();
    valores[2] = datasetTemplate.attribute(2).indexOfValue(usuario.getEstadoCivil());
    valores[3] = datasetTemplate.attribute(3).indexOfValue(usuario.getEscolaridade());
    valores[4] = usuario.getTempoEnderecoAnos();
    valores[5] = usuario.getNivelInadimplenciaEstado();
    valores[6] = datasetTemplate.attribute(6).indexOfValue(usuario.getTipoContratoResidencia());
    valores[7] = datasetTemplate.attribute(7).indexOfValue(usuario.getTipoEmprego());
    valores[8] = usuario.getTempoEmpregoAtual();
    valores[9] = usuario.getSalarioLiquidoMensal();
    valores[10] = usuario.getMontanteInvestimentos();
    valores[11] = usuario.getMontanteBens();
    valores[12] = usuario.getSaldo();
    valores[13] = usuario.getRestanteMensal();
    valores[14] = usuario.isEstaInadimplente() ? 1.0 : 0.0;
    valores[15] = usuario.getValorParcelaAtiva();
    valores[16] = usuario.getMesesAtrasado();
    valores[17] = usuario.getValorCreditoRestanteTotal();

    // classe alvo (seraInadimplente) é desconhecida no momento da previsão
    valores[18] = Utils.missingValue();

    // cria a instância e associa ao dataset
    Instance instancia = new DenseInstance(1.0, valores);
    instancia.setDataset(datasetTemplate); // importante!

    return instancia;
  }

  public double preverScore(Instance instancia) throws Exception {
    return model.classifyInstance(instancia);
  }

  public Instances getTrainingData() {
    return trainingData;
  }

  public double[] probabilidadesScore(Instance instancia) throws Exception {
    return model.distributionForInstance(instancia);
  }
}
