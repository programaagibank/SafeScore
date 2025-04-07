package com.safescore.script;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;
import java.util.Random;

public class TreinamentoModelo {
    public static void main(String[] args) throws Exception {
        // Carrega o dataset
        DataSource source = new DataSource("Modulo1_Projeto/src/main/sources/usuarios.arff");
        Instances data = source.getDataSet();

        // Define a última coluna como a variável alvo (seraInadimplente)
        data.setClassIndex(data.numAttributes() - 1);

        // Cria o modelo
        RandomForest modelo = new RandomForest();
        modelo.buildClassifier(data);

        // Avalia o modelo (opcional)
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(modelo, data, 10, new Random(1));
        System.out.println(eval.toSummaryString());

        // Salva o modelo treinado (opcional)
        weka.core.SerializationHelper.write("modelo_inadimplencia.model", modelo);
    }
}
