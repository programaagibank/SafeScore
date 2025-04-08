package com.safescore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProfileScoreController {

  @FXML
  private Label usuarioScore;

  @FXML
  private Label usuarioNome;

  @FXML
  private TableView<?> verificacoesTable;

  @FXML
  private TableColumn<?, String> colunaCategoria;

  @FXML
  private TableColumn<?, String> colunaResultado;

  @FXML
  public void initialize() {
    // Inicialização básica
    usuarioScore.setText("681");
    usuarioNome.setText("Marcia Teresinha");

  }
}
