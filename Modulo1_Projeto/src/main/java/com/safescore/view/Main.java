package com.safescore;

import weka.core.Instance;
import com.safescore.controller.WekaController;

public class Main {
  public static void main(String[] args) {
    try {
      String cpf = "006.061.768-37"; // exemplo

      WekaController analisador = new WekaController();
      analisador.treinarModelo("Modulo1_Projeto/src/main/sources/usuario_categorizado.arff");

      Instance novoUsuario = analisador.extrairAtributosDeUsuario(cpf);
      double score = analisador.preverScore(novoUsuario);

      System.out.printf("Score do cliente (%s): %.2f\n", cpf, score);
      if (score < 500) {
        System.out.println("⚠️ Risco de crédito: ALTO");
      } else {
        System.out.println("✅ Risco de crédito: BAIXO");
      }

      WekaController analisador1 = new WekaController();
      analisador1.avaliarModelo();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

