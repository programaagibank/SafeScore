package com.safescore.controller;

import com.safescore.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import weka.core.Instance;

public class MainController {

    @FXML
    private TextField cpfField;

    @FXML
    private TextField scoreField;

    private WekaController wekaController = new WekaController();

    @FXML
    private void onLimparClicked() {
        cpfField.clear();
    }

    @FXML
    public void initialize() {
        try {
            wekaController.treinarModelo("Modulo1_Projeto/src/main/sources/usuarios_angelo.arff");
            wekaController.avaliarModelo();
        } catch (Exception e) {
            scoreField.setText("Erro ao treinar modelo.");
            e.printStackTrace();
        }
    }

    @FXML
    private void onPesquisarClicked() {
        String cpf = cpfField.getText();
        if (cpf == null || cpf.isEmpty()) {
            scoreField.setText("CPF inválido.");
            return;
        }

        try {
            Usuario usuarioScore = UsuarioScoreController.definirUsuario(cpf);
            Instance usuarioScoreInstancia = wekaController.converterUsuarioParaInstance(usuarioScore, wekaController.getTrainingData());
            double score = wekaController.preverScore(usuarioScoreInstancia);
            scoreField.setText(String.format("%.0f (Score previsto)", score));
        } catch (Exception e) {
            scoreField.setText("Erro na previsão.");
            e.printStackTrace();
        }
    }
}
