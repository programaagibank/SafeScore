package com.safescore.controller;

import com.safescore.dao.CrudMethods.Read;
import com.safescore.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        configurarCpfField(); // <-- chama a função para configurar o campo
    }


    @FXML
    private void onPesquisarClicked() {
        mensagemLabel.setVisible(false);

        String cpf = cpfField.getText();
        if (cpf == null || cpf.isEmpty()) {
            mostrarMensagemAnimada("Digite um CPF válido.");
            return;
        }

        try {
            Usuario usuarioScore = UsuarioScoreController.definirUsuario(cpf);
            Object[] dadosView = Read.listarDadosView(cpf);
            Instance usuarioScoreInstancia = wekaController.converterUsuarioParaInstance(usuarioScore, wekaController.getTrainingData());
            double score = wekaController.preverScore(usuarioScoreInstancia);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/profile-score.fxml"));
            Parent root = loader.load();
            ProfileScoreController controller = loader.getController();
            controller.setUsuarioData(cpf, score, dadosView);

            // Get the current stage (window)
            Stage currentStage = (Stage) cpfField.getScene().getWindow();

            // Create and show the new stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Perfil do Usuário - SafeScore");
            stage.setWidth(670);
            stage.setHeight(445);
            stage.setResizable(false);
            stage.centerOnScreen();

            // Close the current stage
            currentStage.close();

            // Show the new stage
            stage.show();

        } catch (Exception e) {
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
            } else {
                cpfField.getStyleClass().removeAll("text-field-error", "text-field-success");
                cpfField.getStyleClass().add("text-field-error");
            }
        });
    }
}
