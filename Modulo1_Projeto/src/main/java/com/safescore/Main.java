package com.safescore;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import com.safescore.controller.UsuarioScoreController;

public class Main {
  public static void main(String[] args) {
    try {
      String cpf = "12345678900"; // exemplo

      UsuarioScoreController analisador = new UsuarioScoreController();
      analisador.treinarModelo("Modulo1_Projeto/src/main/sources/usuario.arff");

      Instance novoUsuario = analisador.extrairAtributosDeUsuario(cpf);
      double score = analisador.preverScore(novoUsuario);

      System.out.printf("Score do cliente (%s): %.2f\n", cpf, score);
      if (score < 500) {
        System.out.println("⚠️ Risco de crédito: ALTO");
      } else {
        System.out.println("✅ Risco de crédito: BAIXO");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

