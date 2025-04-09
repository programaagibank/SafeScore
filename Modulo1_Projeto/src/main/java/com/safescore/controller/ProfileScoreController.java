package com.safescore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


public class ProfileScoreController {

  @FXML
  private Label usuarioScore;

  @FXML
  private Label usuarioNome;

  @FXML
  private TableView<Verificacao> verificacoesTable;

  @FXML
  private TableColumn<Verificacao, String> colunaCategoria;

  @FXML
  private TableColumn<Verificacao, String> colunaResultado;

  @FXML
  public void initialize() {
    // Inicialização básica
//    usuarioScore.setText("681");
//    usuarioNome.setText("Marcia Teresinha");
    colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    colunaResultado.setCellValueFactory(new PropertyValueFactory<>("resultado"));
  }

  public void setUsuarioData(String cpf, double score, Object[] dadosView) {
    String estado = String.valueOf(dadosView[0]);
    String idade = String.valueOf(dadosView[1]);
    String nome = String.valueOf(dadosView[2]);
    String entradaTransacao = String.valueOf(dadosView[3]);
    String saidaTransacao = String.valueOf(dadosView[4]);

    ObservableList<Verificacao> lista = FXCollections.observableArrayList();

    lista.add(new Verificacao("Estado", estado));
    lista.add(new Verificacao("CPF", cpf));
    lista.add(new Verificacao("Idade", idade));
    lista.add(new Verificacao("Receita", entradaTransacao));
    lista.add(new Verificacao("Despesas", saidaTransacao));

    verificacoesTable.setItems(lista);

    usuarioNome.setText(nome); // ou o nome do usuário, se tiver
    usuarioScore.setText(String.format("%d", (int) score));
  }
}
