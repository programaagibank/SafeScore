package com.safescore.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserView extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    // Carrega o FXML
    Parent root = FXMLLoader.load(getClass().getResource("/view/main-view.fxml"));

    // Cria a cena
    Scene scene = new Scene(root);

    // Define o tamanho da janela para simular um celular (360x640 pixels)
    primaryStage.setWidth(360);
    primaryStage.setHeight(500);

    // Impede que a janela seja redimensionada (opcional, mas comum para simular um celular)
    primaryStage.setResizable(false);

    // Centraliza a janela na tela
    primaryStage.centerOnScreen();

    // Define a cena e o título
    primaryStage.setScene(scene);
    primaryStage.setTitle("SafeScore App");

    // (Opcional) Remove as bordas da janela para um visual mais "app-like"
    // primaryStage.initStyle(StageStyle.UNDECORATED);

    // Exibe a janela
    primaryStage.show();
  }
}