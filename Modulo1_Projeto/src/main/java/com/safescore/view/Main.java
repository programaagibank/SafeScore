package com.safescore.view;

import com.safescore.dao.CrudMethods.Read;
import com.safescore.dao.db.DBconexao;
import com.safescore.dao.CrudMethods.Update;
import com.safescore.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

    public class main {
        public static void main(String[] args) {

            System.out.println("<>Main<>");

//    System.out.println("\n=== Usuários ===");
//    Read.listarUsuarios();
//
//    System.out.println("\n=== Empregos ===");
//    Read.listarEmpregos();
//
//    System.out.println("\n=== Histórico de Crédito ===");
//    Read.listarHistoricoCredito();
//
//    System.out.println("\n=== Acessos ===");
//    Read.listarAcessos();
//
//    System.out.println("\n=== Patrimônios ===");
//    Read.listarPatrimonios();
//
//    System.out.println("\n=== Scores ===");
//    Read.listarScores();
//
//    System.out.println("\n=== Transações ===");
//    Read.listarTransacoes();
//
//    System.out.println("\n=== Estados Civis ===");
//    Read.listarEstadosCivis();
//
//    System.out.println("\n=== Escolaridades ===");
//    Read.listarEscolaridades();
//
//    System.out.println("\n=== Vínculos Trabalhistas ===");
//    Read.listarVinculosTrabalhistas();
//
//    System.out.println("\n=== Contratos Residenciais ===");
//    Read.listarContratosResidenciais();
//
//    System.out.println("\n=== Endereços ===");
//    Read.listarEnderecos();

            System.out.println("\n=== Dados do Usuário Específico ===");
            Read.listarDadosUsuario("00011122233");

            try (Connection conn = DBconexao.connect()) {
                if (conn != null) {
                    // Testando updateUsuario
                    Update.updateUsuario(conn, "000.412.583-55", "Luiz da Silva", 2, 1, 1);
                    System.out.println("Update de usuario realizado com sucesso.");

//        // Testando updateEndereco
//        Update.updateEndereco(conn, 1, "12345-678", "100", "SP");
//        System.out.println("Update de endereco realizado com sucesso.");
//
//        // Testando updateEmprego
//        Update.updateEmprego(conn, 1, 5000.0, "2023-01-01", "2025-12-31", 1, "12345678900");
//        System.out.println("Update de emprego realizado com sucesso.");
//
//        // Testando updatePatrimonio
//        Update.updatePatrimonio(conn, 1, 10000.0, 50000.0, 20000.0, "12345678900");
//        System.out.println("Update de patrimonio realizado com sucesso.");
//
//        // Testando updateScore
//        Update.updateScore(conn, 1, "2025-03-21", 750, "12345678900");
//        System.out.println("Update de score realizado com sucesso.");
                } else {
                    System.out.println("Falha na conexão com o banco de dados.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao executar update: " + e.getMessage());
            }
        }
    }



    class Main {
    public static void main(String[] args) {
        // Testando a criação de um usuário
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678900");
        usuario.setNumeroDependentes(2);
        usuario.setEscolaridade("Superior Completo");
        usuario.setEstadoCivil("Casado");
        usuario.setScore(700);

        // Salvar o usuário no banco de dados
        usuario.salvarUsuario();

        // Testando a busca do usuário pelo CPF
        Usuario usuarioBuscado = Usuario.buscarUsuario("12345678900");
        if (usuarioBuscado != null) {
            System.out.println("Score do usuário: " + usuarioBuscado.getScore());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
}

