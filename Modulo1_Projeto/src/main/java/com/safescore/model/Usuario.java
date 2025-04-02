package com.safescore.model;

public class Usuario {
    // Atributos privados
    private String cpf;
    private String rangeIdade;
    private int numeroDependentes;
    private String estadoCivil;
    private String escolaridade;
    private int tempoEnderecoAnos;
    private String nivelInadimplenciaEstado;
    private String tipoContratoResidencia;
    private String tipoEmprego;
    private int tempoEmpregoAtual;
    private double salarioLiquidoMensal;
    private double montanteInvestimentos;
    private double montanteBens;
    private double saldo;
    private double restanteMensal;
    private boolean estaInadimplente;
    private double valorParcelaAtiva;
    private int mesesAtrasado;
    private double valorCreditoRestanteTotal;

    // Construtor
        public Usuario(String cpf, String rangeIdade, int numeroDependentes, String estadoCivil, String escolaridade,
                   int tempoEnderecoAnos, String nivelInadimplenciaEstado, String tipoContratoResidencia,
                   String tipoEmprego, int tempoEmpregoAtual, double salarioLiquidoMensal,
                   double montanteInvestimentos, double montanteBens, double saldo, double restanteMensal,
                   boolean estaInadimplente, double valorParcelaAtiva, int mesesAtrasado, double valorCreditoRestanteTotal) {
        this.cpf = cpf;
        this.rangeIdade = rangeIdade;
        this.numeroDependentes = numeroDependentes;
        this.estadoCivil = estadoCivil;
        this.escolaridade = escolaridade;
        this.tempoEnderecoAnos = tempoEnderecoAnos;
        this.nivelInadimplenciaEstado = nivelInadimplenciaEstado;
        this.tipoContratoResidencia = tipoContratoResidencia;
        this.tipoEmprego = tipoEmprego;
        this.tempoEmpregoAtual = tempoEmpregoAtual;
        this.salarioLiquidoMensal = salarioLiquidoMensal;
        this.montanteInvestimentos = montanteInvestimentos;
        this.montanteBens = montanteBens;
        this.saldo = saldo;
        this.restanteMensal = restanteMensal;
        this.estaInadimplente = estaInadimplente;
        this.valorParcelaAtiva = valorParcelaAtiva;
        this.mesesAtrasado = mesesAtrasado;
        this.valorCreditoRestanteTotal = valorCreditoRestanteTotal;
    }

    // Getters e Setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

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

    public String getTipoContratoResidencia() { return tipoContratoResidencia; }
    public void setTipoContratoResidencia(String tipoContratoResidencia) { this.tipoContratoResidencia = tipoContratoResidencia; }

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

    public double getValorParcelaAtiva() { return valorParcelaAtiva; }
    public void setValorParcelaAtiva(double valorParcelaAtiva) { this.valorParcelaAtiva = valorParcelaAtiva; }

    public int getMesesAtrasado() { return mesesAtrasado; }
    public void setMesesAtrasado(int mesesAtrasado) { this.mesesAtrasado = mesesAtrasado; }

    public double getValorCreditoRestanteTotal() { return valorCreditoRestanteTotal; }
    public void setValorCreditoRestanteTotal(double valorCreditoRestanteTotal) { this.valorCreditoRestanteTotal = valorCreditoRestanteTotal; }


}
