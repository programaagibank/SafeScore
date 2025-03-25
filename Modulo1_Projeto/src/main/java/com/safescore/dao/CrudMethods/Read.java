package com.safescore.dao.CrudMethods;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import com.safescore.dao.db.DBconexao;
import java.sql.PreparedStatement;

public class Read {

    public static void listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("CPF: " + rs.getString("cpf") +
                        ", Nome: " + rs.getString("nome") +
                        ", Data de Nascimento: " + rs.getDate("dataNascimento") +
                        ", Dependentes: " + rs.getInt("dependentes") +
                        ", Escolaridade: " + rs.getInt("idEscolaridade") +
                        ", Estado Civil: " + rs.getInt("idEstadoCivil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarEmpregos() {
        String sql = "SELECT * FROM emprego";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Emprego: " + rs.getInt("idEmprego") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Salário Esperado: " + rs.getDouble("salarioEsperado") +
                        ", Data Início: " + rs.getDate("dataInicioEmprego") +
                        ", Data Fim: " + rs.getDate("dataFinalEmprego") +
                        ", Vínculo Trabalhista: " + rs.getInt("idVinculoTrabalista"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarHistoricoCredito() {
        String sql = "SELECT * FROM historicoCredito";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Crédito: " + rs.getInt("idCredito") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Parcelas Restantes: " + rs.getInt("parcelasRestantes") +
                        ", Valor Parcela: " + rs.getDouble("valorParcela") +
                        ", Meses Atrasados: " + rs.getInt("mesesAtrasado") +
                        ", Está Inadimplente: " + rs.getBoolean("estaInadimplente") +
                        ", Valor Crédito Restante: " + rs.getDouble("valorCreditoRestante") +
                        ", Valor Crédito: " + rs.getDouble("valorCredito"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarAcessos() {
        String sql = "SELECT * FROM acessos";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Acesso Diário: " + rs.getInt("idAcessoDiario") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Data e Hora do Acesso: " + rs.getTimestamp("dataHoraAcesso"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarPatrimonios() {
        String sql = "SELECT * FROM patrimonio";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Patrimônio: " + rs.getInt("idPatrimonio") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Investimentos: " + rs.getDouble("montanteInvestimentos") +
                        ", Bens: " + rs.getDouble("montanteBens") +
                        ", Saldo: " + rs.getDouble("saldo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarScores() {
        String sql = "SELECT * FROM score";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Score: " + rs.getInt("idScore") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Data Cálculo: " + rs.getDate("dataCalculo") +
                        ", Score: " + rs.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarTransacoes() {
        String sql = "SELECT * FROM transacao";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Transação: " + rs.getInt("idTransacao") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Data Recorte: " + rs.getDate("dataRecorteTransacao") +
                        ", Valor Entrada: " + rs.getDouble("valorEntrada") +
                        ", Valor Saída: " + rs.getDouble("valorSaida"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarEstadosCivis() {
        String sql = "SELECT * FROM estadoCivil";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Estado Civil: " + rs.getInt("idEstadoCivil") +
                        ", Estado Civil: " + rs.getString("estadoCivil"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarEscolaridades() {
        String sql = "SELECT * FROM escolaridade";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Escolaridade: " + rs.getInt("idEscolaridade") +
                        ", Escolaridade: " + rs.getString("escolaridade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarVinculosTrabalhistas() {
        String sql = "SELECT * FROM vinculoTrabalhista";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Vínculo Trabalhista: " + rs.getInt("idVinculoTrabalhista") +
                        ", Vínculo: " + rs.getString("vinculo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarContratosResidenciais() {
        String sql = "SELECT * FROM contratoResidencial";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Contrato Residencial: " + rs.getInt("idContratoResidencial") +
                        ", CPF: " + rs.getString("cpf") +
                        ", Data Inicial: " + rs.getDate("dataInicialEndereco") +
                        ", Data Final: " + rs.getDate("dataFinalEndereco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarEnderecos() {
        String sql = "SELECT * FROM endereco";
        try (Connection conn = DBconexao.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID Endereço: " + rs.getInt("idEndereco") +
                        ", CEP: " + rs.getString("cep") +
                        ", Número: " + rs.getInt("numero") +
                        ", Estado: " + rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listarDadosUsuario(String cpf) {
        System.out.println("\n=== Dados do Usuário CPF: " + cpf + " ===");

        String sqlUsuario = "SELECT * FROM usuario WHERE cpf = ?";
        String sqlEmprego = "SELECT * FROM emprego WHERE cpf = ?";
        String sqlHistoricoCredito = "SELECT * FROM historicoCredito WHERE cpf = ?";
        String sqlAcessos = "SELECT * FROM acessos WHERE cpf = ?";
        String sqlPatrimonio = "SELECT * FROM patrimonio WHERE cpf = ?";
        String sqlScore = "SELECT * FROM score WHERE cpf = ?";
        String sqlTransacao = "SELECT * FROM transacao WHERE cpf = ?";
        String sqlContratoResidencial = "SELECT * FROM contratoResidencial WHERE cpf = ?";

        try (Connection conn = DBconexao.connect()) {
            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("CPF: " + rs.getString("cpf") +
                            ", Nome: " + rs.getString("nome") +
                            ", Data de Nascimento: " + rs.getDate("dataNascimento") +
                            ", Dependentes: " + rs.getInt("dependentes") +
                            ", Escolaridade: " + rs.getInt("idEscolaridade") +
                            ", Estado Civil: " + rs.getInt("idEstadoCivil"));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlEmprego)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Emprego -> ID: " + rs.getInt("idEmprego") +
                            ", Salário Esperado: " + rs.getDouble("salarioEsperado") +
                            ", Data Início: " + rs.getDate("dataInicioEmprego") +
                            ", Data Fim: " + rs.getDate("dataFinalEmprego") +
                            ", Vínculo Trabalhista: " + rs.getInt("idVinculoTrabalhista"));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlHistoricoCredito)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Histórico Crédito -> ID: " + rs.getInt("idCredito") +
                            ", Parcelas Restantes: " + rs.getInt("parcelasRestantes") +
                            ", Valor Parcela: " + rs.getDouble("valorParcela") +
                            ", Meses Atrasados: " + rs.getInt("mesesAtrasado") +
                            ", Inadimplente: " + rs.getBoolean("estaInadimplente") +
                            ", Valor Crédito Restante: " + rs.getDouble("valorCreditoRestante"));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlAcessos)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Acessos -> ID Acesso Diário: " + rs.getInt("idAcessoDiario") +
                            ", Data e Hora do Acesso: " + rs.getTimestamp("dataHoraAcesso"));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlPatrimonio)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Patrimônio -> ID: " + rs.getInt("idPatrimonio") +
                            ", Investimentos: " + rs.getDouble("montanteInvestimentos") +
                            ", Bens: " + rs.getDouble("montanteBens") +
                            ", Saldo: " + rs.getDouble("saldo"));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlScore)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Score -> ID: " + rs.getInt("idScore") +
                            ", Data Cálculo: " + rs.getDate("dataCalculo") +
                            ", Score: " + rs.getInt("score"));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlTransacao)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Transação -> ID: " + rs.getInt("idTransacao") +
                            ", Data Recorte: " + rs.getDate("dataRecorteTransacao") +
                            ", Valor Entrada: " + rs.getDouble("valorEntrada") +
                            ", Valor Saída: " + rs.getDouble("valorSaida"));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlContratoResidencial)) {
                stmt.setString(1, cpf);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Contrato Residencial -> ID: " + rs.getInt("idContratoResidencial") +
                            ", Data Inicial: " + rs.getDate("dataInicialEndereco") +
                            ", Data Final: " + rs.getDate("dataFinalEndereco"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
