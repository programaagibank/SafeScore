package com.safescore.controller;

import com.safescore.model.Verificacao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Map;


public class ProfileScoreController {

  @FXML
  private Label usuarioScore;

  @FXML
  private Label usuarioNome;

  @FXML
  private Label pontoBom;

  @FXML
  private Label pontoMedio;

  @FXML
  private Label pontoRuin;

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

  public void setUsuarioData(String cpf, double score, Object[] dadosView, Map<String, String> fatores) {
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

    pontoBom.setText(fatores.getOrDefault("positivo", "Nenhum fator positivo identificado"));
    pontoRuin.setText(fatores.getOrDefault("medio", "Nenhum fator médio identificado"));
    pontoMedio.setText(fatores.getOrDefault("negativo", "Nenhum fator negativo identificado"));

    // Altera a cor de fundo do label baseado no score
    String corBackground;
    if (score < 400) {
      corBackground = "#e74c3c"; // vermelho
    } else if (score < 600) {
      corBackground = "#f1c40f"; // amarelo
    } else {
      corBackground = "#2ecc71"; // verde
    }

    usuarioScore.setStyle(
            "-fx-background-color: " + corBackground + ";" +
                    "-fx-background-radius: 4%;" +
                    "-fx-text-alignment: center;" +
                    "-fx-alignment: center;" +
                    "-fx-text-fill: white;"
    );
  }
}
