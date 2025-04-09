package com.safescore.controller;

import com.safescore.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import weka.core.Instance;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private TextField cpfField;

    @FXML
    private TextField scoreField;

    @FXML
    private Label mensagemLabel;

    @FXML
    private Button pesquisarButton;

    private WekaController wekaController = new WekaController();

    @FXML
    private void onLimparClicked() {
        cpfField.clear();
        mensagemLabel.setText("");
        mensagemLabel.setVisible(false);
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
        pesquisarButton.setDisable(true);
        configurarCpfField(); // <-- chama a função para configurar o campo
    }

    @FXML
    private void onPesquisarClicked() {
        mensagemLabel.setVisible(false); // Esconde a mensagem ao começar

        String cpf = cpfField.getText();
        if (cpf == null || cpf.isEmpty()) {
            aplicarEfeitoShake(cpfField);
            mostrarMensagemAnimada("Digite um CPF válido.");
            return;
        }

        try {
            Usuario usuarioScore = UsuarioScoreController.definirUsuario(cpf);
            Instance usuarioScoreInstancia = wekaController.converterUsuarioParaInstance(usuarioScore, wekaController.getTrainingData());
            double score = wekaController.preverScore(usuarioScoreInstancia);
            scoreField.setText(String.format("%.0f (Score previsto)", score));
        } catch (Exception e) {
            aplicarEfeitoShake(cpfField);
            mostrarMensagemAnimada("Usuário não encontrado.");
            e.printStackTrace();
        }
    }

    private void mostrarMensagemAnimada(String mensagem) {
        mensagemLabel.setText(mensagem);
        mensagemLabel.setOpacity(0); // Começa invisível
        mensagemLabel.setVisible(true);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), mensagemLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void configurarCpfField() {
        cpfField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove tudo que não é número
            String somenteNumeros = newValue.replaceAll("[^\\d]", "");

            // Limita a 11 dígitos
            if (somenteNumeros.length() > 11) {
                somenteNumeros = somenteNumeros.substring(0, 11);
            }

            // Aplica a máscara de CPF: 000.000.000-00
            StringBuilder cpfFormatado = new StringBuilder();
            for (int i = 0; i < somenteNumeros.length(); i++) {
                cpfFormatado.append(somenteNumeros.charAt(i));
                if (i == 2 || i == 5) {
                    cpfFormatado.append(".");
                } else if (i == 8) {
                    cpfFormatado.append("-");
                }
            }

            // Evita loop: só atualiza se mudar de verdade
            if (!cpfFormatado.toString().equals(newValue)) {
                cpfField.setText(cpfFormatado.toString());
                cpfField.positionCaret(cpfFormatado.length()); // Mantém o cursor no final
            }

            // Verificação de borda
            if (somenteNumeros.length() == 11) {
                cpfField.getStyleClass().removeAll("text-field-error", "text-field-success");
                cpfField.getStyleClass().add("text-field-success");
                pesquisarButton.setDisable(false);
            } else {
                cpfField.getStyleClass().removeAll("text-field-error", "text-field-success");
                cpfField.getStyleClass().add("text-field-error");
                pesquisarButton.setDisable(true);
            }
        });
    }

    private void aplicarEfeitoShake(TextField field) {
        // Cria o efeito de deslocamento lateral
        javafx.animation.TranslateTransition shake = new javafx.animation.TranslateTransition(Duration.millis(50), field);
        shake.setFromY(0);
        shake.setByY(10);
        shake.setCycleCount(6); // quantas vezes vai para frente e para trás
        shake.setAutoReverse(true);
        shake.play();
    }
}
