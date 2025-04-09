package com.safescore.controller;

import com.safescore.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import weka.core.Instance;

public class MainController {

    @FXML
    private TextField cpfField;

    @FXML
    private TextField scoreField;

    @FXML
    private Label mensagemLabel;

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
        mensagemLabel.setVisible(false); // Esconde a mensagem ao começar

        String cpf = cpfField.getText();
        if (cpf == null || cpf.isEmpty()) {
            mensagemLabel.setText("Digite um CPF válido.");
            mensagemLabel.setVisible(true);
            return;
        }

        try {
            Usuario usuarioScore = UsuarioScoreController.definirUsuario(cpf);
            Instance usuarioScoreInstancia = wekaController.converterUsuarioParaInstance(usuarioScore, wekaController.getTrainingData());
            double score = wekaController.preverScore(usuarioScoreInstancia);
            scoreField.setText(String.format("%.0f (Score previsto)", score));
        } catch (Exception e) {
            mensagemLabel.setText("Usuário não encontrado.");
            mensagemLabel.setVisible(true);
            e.printStackTrace();
        }
    }

}
