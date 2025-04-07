package com.safescore.controller;

import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;

public class PrevisaoController {
    public static void main(String[] args) throws Exception {
        // Carrega o modelo treinado
        Classifier modelo = (Classifier) weka.core.SerializationHelper.read("modelo_inadimplencia.model");

        // Carrega o dataset para pegar a estrutura (header)
        DataSource source = new DataSource("caminho/para/seu/arquivo.arff");
        Instances estrutura = source.getDataSet();
        estrutura.setClassIndex(estrutura.numAttributes() - 1);

        // Cria uma nova instância (novo usuário)
        Instance novoUsuario = new DenseInstance(estrutura.numAttributes());
        novoUsuario.setDataset(estrutura);

        // Preencher os atributos aqui (exemplo)
        novoUsuario.setValue(0, "26 a 40"); // rangeIdade
        novoUsuario.setValue(1, 2);         // numeroDependentes
        novoUsuario.setValue(2, "Solteiro"); // estadoCivil
        novoUsuario.setValue(3, "Superior Incompleto"); // escolaridade
        novoUsuario.setValue(4, 5);         // tempoEnderecoAnos
        novoUsuario.setValue(5, 50);        // nivelInadimplenciaEstado
        novoUsuario.setValue(6, "Locatario"); // tipoContratoResidencia
        novoUsuario.setValue(7, "CLT");      // tipoEmprego
        novoUsuario.setValue(8, 3);          // tempoEmpregoAtual
        novoUsuario.setValue(9, 3000);       // salarioLiquidoMensal
        novoUsuario.setValue(10, 0);         // montanteInvestimentos
        novoUsuario.setValue(11, 0);         // montanteBens
        novoUsuario.setValue(12, 5000);      // saldo
        novoUsuario.setValue(13, 2000);      // restanteMensal
        novoUsuario.setValue(14, 0);          // estaInadimplente
        novoUsuario.setValue(15, 0);          // valorParcelaAtiva
        novoUsuario.setValue(16, 0);          // mesesAtrasado
        novoUsuario.setValue(17, 0);          // valorCreditoRestanteTotal
        // atributo 18 ("seraInadimplente") é o alvo, então NÃO seta!

        // Faz a previsão
        double[] distribuicao = modelo.distributionForInstance(novoUsuario);

        // Mostra a probabilidade de cada classe
        System.out.println("Probabilidade de NÃO ser inadimplente: " + distribuicao[0]);
        System.out.println("Probabilidade de SER inadimplente: " + distribuicao[1]);
    }
}

