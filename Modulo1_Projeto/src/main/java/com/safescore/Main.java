package com.safescore;

import com.safescore.controller.UsuarioScoreController;
import com.safescore.model.Usuario;
import weka.core.Instance;
import com.safescore.controller.WekaController;

public class Main {
  public static void main(String[] args) {
    try {
      WekaController analisador = new WekaController();

      analisador.treinarModelo("Modulo1_Projeto/src/main/sources/usuarios_angelo.arff");

      analisador.avaliarModelo();

//      String cpf = UsuarioScoreController.entradaCPF();//Caso Real

      String cpfTeste = "007.412.024-76";
//              "030.116.428-26";

      Usuario usuarioScore = UsuarioScoreController.definirUsuario(cpfTeste);

      Instance usuarioScoreInstancia = analisador.converterUsuarioParaInstance(usuarioScore, analisador.getTrainingData());

      int score = (int) analisador.preverScore(usuarioScoreInstancia);

      System.out.printf(String.valueOf(score));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}