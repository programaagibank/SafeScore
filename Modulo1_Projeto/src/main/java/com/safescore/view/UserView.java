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

    Scene scene = new Scene(root);

    primaryStage.setWidth(360);
    primaryStage.setHeight(500);

    primaryStage.setResizable(false);

    primaryStage.centerOnScreen();

    primaryStage.setScene(scene);
    primaryStage.setTitle("SafeScore App");

    // primaryStage.initStyle(StageStyle.UNDECORATED);

    primaryStage.show();
  }
}