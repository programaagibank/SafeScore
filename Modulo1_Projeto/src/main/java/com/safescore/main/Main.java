package com.safescore.main;

import com.safescore.model.Usuario;

public class Main {
    public static void main(String[] args) {
        // Testando a criação de um usuário
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678900");
        usuario.setNome("João Silva");
        usuario.setNumeroDependentes(2);
        usuario.setEscolaridade("Superior Completo");
        usuario.setEstadoCivil("Casado");
        usuario.setScore(700);

        // Salvar o usuário no banco de dados
        usuario.salvarUsuario();

        // Testando a busca do usuário pelo CPF
        Usuario usuarioBuscado = Usuario.buscarUsuario("12345678900");
        if (usuarioBuscado != null) {
            System.out.println("Usuário encontrado: " + usuarioBuscado.getNome());
            System.out.println("Score do usuário: " + usuarioBuscado.getScore());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
}
