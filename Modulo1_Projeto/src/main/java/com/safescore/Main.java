package com.safescore;

import com.safescore.controller.UsuarioScoreController;
import com.safescore.model.Usuario;
import com.safescore.view.UserView;
import javafx.application.Application;
import weka.core.Instance;
import com.safescore.controller.WekaController;

public class Main {
  public static void main(String[] args) {
    try {
      WekaController analisador = new WekaController();

      Application.launch(UserView.class, args);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}