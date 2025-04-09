package com.safescore.model;

public class FatorScore {
  private String descricao;
  private double impacto;

  public FatorScore(String descricao, double impacto) {
    this.descricao = descricao;
    this.impacto = impacto;
  }

  public String getDescricao() {
    return descricao;
  }

  public double getImpacto() {
    return impacto;
  }
}

