package com.safescore.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SafeScore");

        // Título da aplicação
        Label titulo = new Label("SafeScore");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Campo de CPF
        Label cpfLabel = new Label("Digite o CPF:");
        TextField cpfField = new TextField();
        cpfField.setPromptText("000.000.000-00");

        // Botão de pesquisa
        Button pesquisarButton = new Button("Pesquisar");

        // Campo para exibir o score
        Label resultadoLabel = new Label("Score do cliente:");
        TextField scoreField = new TextField();
        scoreField.setEditable(false);

        // Ação do botão
        pesquisarButton.setOnAction(e -> {
            String cpf = cpfField.getText();
            // Aqui é onde você faria a lógica de busca real no banco de dados
            if (cpf.isEmpty()) {
                scoreField.setText("CPF inválido.");
            } else {
                // ⚠️ Exemplo fixo, depois pode conectar com seu DAO
                scoreField.setText("750 (Score simulado para CPF: " + cpf + ")");
            }
        });

        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(titulo, cpfLabel, cpfField, pesquisarButton, resultadoLabel, scoreField);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
