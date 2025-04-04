package com.safescore;

import com.safescore.controller.UsuarioScoreController;
import com.safescore.model.Usuario;
import weka.core.Instance;
import com.safescore.controller.WekaController;

public class Main {
    public static void main(String[] args) {
        try {
            //INICIAR JAVAFX STAGE
            String cpf = UsuarioScoreController.entradaCPF();//Caso Real

            String cpfTeste = "006.061.768-37"; // exemplo

            Usuario usuarioScore = UsuarioScoreController.definirUsuario(cpfTeste);

            //Transformar o usuario Score em Instancia
            Instance usuarioScoreInstancia = (Instance) usuarioScore;

            //Criar o Score
            //double score = analisador.preverScore(usuarioScoreInstancia)

            WekaController analisador = new WekaController();

            //modelo ja estaria treinado
            analisador.treinarModelo("Modulo1_Projeto/src/main/sources/usuario_categorizado.arff");

            Instance novoUsuario = analisador.extrairAtributosDeUsuario(cpfTeste);
            double score = analisador.preverScore(novoUsuario);

            System.out.printf("Score do cliente (%s): %.2f\n", cpfTeste, score);
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