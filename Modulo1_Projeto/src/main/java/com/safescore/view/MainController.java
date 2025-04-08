package com.safescore.view;

import com.safescore.controller.WekaController;
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
    public void initialize() {
        try {
            wekaController.treinarModelo("src/main/sources/usuario_categorizado.arff");
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
            Instance usuario = wekaController.extrairAtributosDeUsuario(cpf);
            double score = wekaController.preverScore(usuario);
            scoreField.setText(String.format("%.0f (Score previsto)", score));
        } catch (Exception e) {
            scoreField.setText("Erro na previsão.");
            e.printStackTrace();
        }
    }
}
