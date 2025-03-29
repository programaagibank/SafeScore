package com.safescore.dao.CrudMethods;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.safescore.dao.db.DBconexao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

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

  public static Object[] listarUsuarios(String cpf) {
    String sql = "SELECT * FROM usuario WHERE cpf = ?";
    Object[] usuario = new Object[6];

    try (Connection conn = DBconexao.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, cpf);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        usuario = new Object[6];
        usuario[0] = rs.getString("cpf");
        usuario[1] = rs.getString("nome");
        usuario[2] = rs.getDate("dataNascimento");
        usuario[3] = rs.getInt("dependentes");
        usuario[4] = rs.getInt("idEscolaridade");
        usuario[5] = rs.getInt("idEstadoCivil");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return usuario;
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

  public static List<Object[]> listarEmpregos(String cpf) {
    String sql = "SELECT * FROM emprego WHERE cpf = ?";
    List<Object[]> empregos = new ArrayList<>();

    try (Connection conn = DBconexao.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, cpf);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        Object[] emprego = new Object[6];
        emprego[0] = rs.getInt("idEmprego");
        emprego[1] = rs.getDouble("salarioEsperado");
        emprego[2] = rs.getDate("dataInicioEmprego");
        emprego[3] = rs.getDate("dataFinalEmprego");
        emprego[4] = rs.getInt("idVinculoTrabalhista");
        emprego[5] = rs.getString("cpf");


        empregos.add(emprego);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return empregos;
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

  public static Object[] listarEstadoCivil(int idEstadoCivil) {
    String sql = "SELECT * FROM estadoCivil WHERE idEstadoCivil = ?";
    Object[] estadoCivil = new Object[2];

    try (Connection conn = DBconexao.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, idEstadoCivil);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        estadoCivil = new Object[2];
        estadoCivil[0] = rs.getString("idEstadoCivil");
        estadoCivil[1] = rs.getString("estadoCivil");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return estadoCivil;
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

  public static Object[] listarEscolaridades(int idEscolaridade) {
    String sql = "SELECT * FROM escolaridade WHERE idEscolaridade = ?";
    Object[] escolaridade = new Object[2];

    try (Connection conn = DBconexao.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, idEscolaridade);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        escolaridade = new Object[2];
        escolaridade[0] = rs.getString("idEscolaridade");
        escolaridade[1] = rs.getString("escolaridade");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return escolaridade;
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

  public static Object[] listarVinculoTrabalhista(int idVinculoTrabalhista) {
    String sql = "SELECT * FROM vinculoTrabalhista WHERE idVinculoTrabalhista = ?";
    Object[] vinculo = new Object[2];

    try (Connection conn = DBconexao.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, idVinculoTrabalhista);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        vinculo = new Object[2];
        vinculo[0] = rs.getInt("idVinculoTrabalhista"); // ID como String (igual ao exemplo)
        vinculo[1] = rs.getString("vinculo"); // Nome do vínculo
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return vinculo;
  }


  public static List<Object[]> listarContratosResidenciais(String cpf) {
    String sql = "SELECT * FROM contratoResidencial WHERE cpf = ?";
    List<Object[]> contratos = new ArrayList<>();

    try (Connection conn = DBconexao.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, cpf);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        Object[] contrato = new Object[6];
        contrato[0] = rs.getInt("idContratoResidencial");
        contrato[1] = rs.getString("cpf");
        contrato[2] = rs.getDate("dataInicialEndereco");
        contrato[3] = rs.getDate("dataFinalEndereco");
        contrato[4] = rs.getInt("idTipoContratoResidencial");
        contrato[5] = rs.getInt("idEndereco");

        contratos.add(contrato);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return contratos;
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

  public static Object[] listarTipoContratoResidencial(int idTipoContratoResidencial) {
    String sql = "SELECT * FROM tipoContratoResidencial where idTipoContratoResidencial = " + idTipoContratoResidencial;
    Object[] tipo = new Object[3];
    try (Connection conn = DBconexao.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        tipo[0] = rs.getString("idTipoContratoResidencial");
        tipo[1] = rs.getString("tipo");;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return tipo;
  }

  public static Object[] listarEnderecos(int idEndereco) {
    String sql = "SELECT * FROM endereco where idEndereco = " + idEndereco;
    Object[] endereco = new Object[3];
    try (Connection conn = DBconexao.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        endereco[0] = rs.getString("cep");
        endereco[1] = rs.getInt("numero");
        endereco[2] = rs.getString("estado");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return endereco;
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
