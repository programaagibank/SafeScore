package com.safescore.dao.CrudMethods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.safescore.dao.db.DBconexao;

public class Delete {
    public static void deletarUsuario(String cpf) {
        String sqlDeleteUsuario = "DELETE FROM usuario WHERE cpf = ?";
        String sqlDeleteContratos = "DELETE FROM contratoResidencial WHERE cpf = ?";
        String sqlDeleteEmpregos = "DELETE FROM emprego WHERE cpf = ?";
        String sqlDeleteAcessos = "DELETE FROM acessos WHERE cpf = ?";
        String sqlDeleteHistoricoCredito = "DELETE FROM historicoCredito WHERE cpf = ?";
        String sqlDeletePatrimonio = "DELETE FROM patrimonio WHERE cpf = ?";
        String sqlDeleteScore = "DELETE FROM score WHERE cpf = ?";
        String sqlDeleteTransacoes = "DELETE FROM transacao WHERE cpf = ?";
        String sqlDeleteEndereco = "DELETE FROM endereco WHERE idEndereco IN (SELECT idEndereco FROM contratoResidencial WHERE cpf = ?)";
        String sqlDeleteTipoContrato = "DELETE FROM tipoContratoResidencial WHERE idTipoContratoResidencial IN (SELECT idTipoContratoResidencial FROM contratoResidencial WHERE cpf = ?)";
        String sqlDeleteVinculoTrabalhista = "DELETE FROM vinculoTrabalhista WHERE idVinculoTrabalhista IN (SELECT idVinculoTrabalhista FROM emprego WHERE cpf = ?)";

        try (Connection conn = DBconexao.connect()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtContratos = conn.prepareStatement(sqlDeleteContratos);
                 PreparedStatement stmtEmpregos = conn.prepareStatement(sqlDeleteEmpregos);
                 PreparedStatement stmtAcessos = conn.prepareStatement(sqlDeleteAcessos);
                 PreparedStatement stmtHistoricoCredito = conn.prepareStatement(sqlDeleteHistoricoCredito);
                 PreparedStatement stmtPatrimonio = conn.prepareStatement(sqlDeletePatrimonio);
                 PreparedStatement stmtScore = conn.prepareStatement(sqlDeleteScore);
                 PreparedStatement stmtTransacoes = conn.prepareStatement(sqlDeleteTransacoes);
                 PreparedStatement stmtEndereco = conn.prepareStatement(sqlDeleteEndereco);
                 PreparedStatement stmtTipoContrato = conn.prepareStatement(sqlDeleteTipoContrato);
                 PreparedStatement stmtVinculoTrabalhista = conn.prepareStatement(sqlDeleteVinculoTrabalhista);
                 PreparedStatement stmtUsuario = conn.prepareStatement(sqlDeleteUsuario)) {


                stmtContratos.setString(1, cpf);
                stmtEmpregos.setString(1, cpf);
                stmtAcessos.setString(1, cpf);
                stmtHistoricoCredito.setString(1, cpf);
                stmtPatrimonio.setString(1, cpf);
                stmtScore.setString(1, cpf);
                stmtTransacoes.setString(1, cpf);
                stmtEndereco.setString(1, cpf);
                stmtTipoContrato.setString(1, cpf);
                stmtVinculoTrabalhista.setString(1, cpf);
                stmtUsuario.setString(1, cpf);

                stmtContratos.executeUpdate();
                stmtEmpregos.executeUpdate();
                stmtAcessos.executeUpdate();
                stmtHistoricoCredito.executeUpdate();
                stmtPatrimonio.executeUpdate();
                stmtScore.executeUpdate();
                stmtTransacoes.executeUpdate();
                stmtEndereco.executeUpdate();
                stmtTipoContrato.executeUpdate();
                stmtVinculoTrabalhista.executeUpdate();

                int linhasAfetadas = stmtUsuario.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Usuário e suas informações foram deletados com sucesso.");
                    conn.commit();

                    System.out.println("Nenhum usuário encontrado com esse CPF.");
                    conn.rollback();
                }
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
