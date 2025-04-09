package com.safescore.controller;

import com.safescore.dao.CrudMethods.Read;
import com.safescore.model.Usuario;
import com.safescore.script.automaticCreditRisk;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weka.core.Instance;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.util.Map;

public class MainController {

    @FXML
    private TextField cpfField;

    @FXML
    private TextField scoreField;

    @FXML
    private ProgressIndicator loadingIndicator;

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
        mensagemLabel.setVisible(false);

        String cpf = cpfField.getText();
        if (cpf == null || cpf.isEmpty() || cpf.replaceAll("[^\\d]", "").length() != 11) {
            aplicarEfeitoShake(cpfField);
            mostrarMensagemAnimada("Digite um CPF válido.");
            return;
        }

        loadingIndicator.setVisible(true); // <-- MOSTRAR o loading

        // Executar a busca e carregamento de forma assíncrona para evitar travar a tela
        new Thread(() -> {
            try {
                Usuario usuarioScore = UsuarioScoreController.definirUsuario(cpf);
                Object[] dadosView = Read.listarDadosView(cpf);
                Instance usuarioScoreInstancia = wekaController.converterUsuarioParaInstance(usuarioScore, wekaController.getTrainingData());
                double score = wekaController.preverScore(usuarioScoreInstancia);
                Map<String, String> fatores = automaticCreditRisk.extrairFatores(usuarioScore.getAllData());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/profile-score.fxml"));
                Parent root = loader.load();
                ProfileScoreController controller = loader.getController();
                controller.setUsuarioData(cpf, score, dadosView, fatores);

                // Executa troca de tela no JavaFX Application Thread
                javafx.application.Platform.runLater(() -> {
                    try {
                        Stage currentStage = (Stage) cpfField.getScene().getWindow();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Perfil do Usuário - SafeScore");
                        stage.setWidth(670);
                        stage.setHeight(445);
                        stage.setResizable(false);
                        stage.centerOnScreen();
                        currentStage.close();
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

            } catch (Exception e) {
                javafx.application.Platform.runLater(() -> {
                    loadingIndicator.setVisible(false); // Some o loading se erro
                    aplicarEfeitoShake(cpfField);
                    mostrarMensagemAnimada("Usuário não encontrado.");
                });
                e.printStackTrace();
            }
        }).start();
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
        field.getStyleClass().removeAll("text-field-success");
        field.getStyleClass().add("text-field-error");
        // Cria o efeito de deslocamento lateral
        javafx.animation.TranslateTransition shake = new javafx.animation.TranslateTransition(Duration.millis(50), field);
        shake.setFromY(0);
        shake.setByY(10);
        shake.setCycleCount(6); // quantas vezes vai para frente e para trás
        shake.setAutoReverse(true);
        shake.setOnFinished(event -> {
            // Depois do shake, volta para o estado original (borda normal de erro)
            field.setTranslateX(0);
        });

        // Tocar beep (alerta sonoro)
        java.awt.Toolkit.getDefaultToolkit().beep();
        shake.play();
    }
}
