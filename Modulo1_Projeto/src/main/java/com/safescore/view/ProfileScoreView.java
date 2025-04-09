package com.safescore.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfileScoreView extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/view/profile-score.fxml"));
    Scene scene = new Scene(root);

    primaryStage.setWidth(670);
    primaryStage.setHeight(445);
    primaryStage.setResizable(false);
    primaryStage.centerOnScreen();
    primaryStage.setScene(scene);
    primaryStage.setTitle("Perfil do Usuário - SafeScore");
    primaryStage.show();
  }
}
