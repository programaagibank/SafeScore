package com.safescore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.safescore.sql.DBconexao;

public class Usuario {
    // Atributos privados
    private String cpf;
    private String nome;
    private String rangeIdade;
    private int numeroDependentes;
    private String estadoCivil;
    private String escolaridade;
    private int tempoEnderecoAnos;
    private String nivelInadimplenciaEstado;
    private double taxaCAGED;
    private String tipoContratoResidencia;
    private double taxaSELIC;
    private String tipoEmprego;
    private int tempoEmpregoAtual;
    private double salarioLiquidoMensal;
    private double montanteInvestimentos;
    private double montanteBens;
    private double saldo;
    private double restanteMensal;
    private boolean estaInadimplente;
    private double valorParcela;
    private int mesesAtrasado;
    private double valorCreditoRestante;
    private int score;

    // Construtor
    public Usuario() {}

    // Método para buscar usuário pelo CPF
    public static Usuario buscarUsuario(String cpf) {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";
        try (Connection conn = DBconexao.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCpf(rs.getString("cpf"));
                usuario.setNome(rs.getString("nome"));
                usuario.setNumeroDependentes(rs.getInt("dependentes"));
                usuario.setEscolaridade(rs.getString("escolaridade"));
                usuario.setEstadoCivil(rs.getString("estadoCivil"));
                usuario.setScore(rs.getInt("score")); // Adicionando o campo score
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar o usuário
    }

    // Getters e Setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRangeIdade() { return rangeIdade; }
    public void setRangeIdade(String rangeIdade) { this.rangeIdade = rangeIdade; }

    public int getNumeroDependentes() { return numeroDependentes; }
    public void setNumeroDependentes(int numeroDependentes) { this.numeroDependentes = numeroDependentes; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getEscolaridade() { return escolaridade; }
    public void setEscolaridade(String escolaridade) { this.escolaridade = escolaridade; }

    public int getTempoEnderecoAnos() { return tempoEnderecoAnos; }
    public void setTempoEnderecoAnos(int tempoEnderecoAnos) { this.tempoEnderecoAnos = tempoEnderecoAnos; }

    public String getNivelInadimplenciaEstado() { return nivelInadimplenciaEstado; }
    public void setNivelInadimplenciaEstado(String nivelInadimplenciaEstado) { this.nivelInadimplenciaEstado = nivelInadimplenciaEstado; }

    public double getTaxaCAGED() { return taxaCAGED; }
    public void setTaxaCAGED(double taxaCAGED) { this.taxaCAGED = taxaCAGED; }

    public String getTipoContratoResidencia() { return tipoContratoResidencia; }
    public void setTipoContratoResidencia(String tipoContratoResidencia) { this.tipoContratoResidencia = tipoContratoResidencia; }

    public double getTaxaSELIC() { return taxaSELIC; }
    public void setTaxaSELIC(double taxaSELIC) { this.taxaSELIC = taxaSELIC; }

    public String getTipoEmprego() { return tipoEmprego; }
    public void setTipoEmprego(String tipoEmprego) { this.tipoEmprego = tipoEmprego; }

    public int getTempoEmpregoAtual() { return tempoEmpregoAtual; }
    public void setTempoEmpregoAtual(int tempoEmpregoAtual) { this.tempoEmpregoAtual = tempoEmpregoAtual; }

    public double getSalarioLiquidoMensal() { return salarioLiquidoMensal; }
    public void setSalarioLiquidoMensal(double salarioLiquidoMensal) { this.salarioLiquidoMensal = salarioLiquidoMensal; }

    public double getMontanteInvestimentos() { return montanteInvestimentos; }
    public void setMontanteInvestimentos(double montanteInvestimentos) { this.montanteInvestimentos = montanteInvestimentos; }

    public double getMontanteBens() { return montanteBens; }
    public void setMontanteBens(double montanteBens) { this.montanteBens = montanteBens; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public double getRestanteMensal() { return restanteMensal; }
    public void setRestanteMensal(double restanteMensal) { this.restanteMensal = restanteMensal; }

    public boolean isEstaInadimplente() { return estaInadimplente; }
    public void setEstaInadimplente(boolean estaInadimplente) { this.estaInadimplente = estaInadimplente; }

    public double getValorParcela() { return valorParcela; }
    public void setValorParcela(double valorParcela) { this.valorParcela = valorParcela; }

    public int getMesesAtrasado() { return mesesAtrasado; }
    public void setMesesAtrasado(int mesesAtrasado) { this.mesesAtrasado = mesesAtrasado; }

    public double getValorCreditoRestante() { return valorCreditoRestante; }
    public void setValorCreditoRestante(double valorCreditoRestante) { this.valorCreditoRestante = valorCreditoRestante; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    // Método para salvar usuário no banco
    public void salvarUsuario() {
        String sql = "INSERT INTO usuario (cpf, nome, dependentes, escolaridade, estadoCivil, score) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBconexao.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.cpf);
            pstmt.setString(2, this.nome);
            pstmt.setInt(3, this.numeroDependentes);
            pstmt.setString(4, this.escolaridade);
            pstmt.setString(5, this.estadoCivil);
            pstmt.setInt(6, this.score); // Incluindo o campo score
            pstmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }
}
