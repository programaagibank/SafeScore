package com.safescore.controller;

public class Verificacao {
  private String categoria;
  private String resultado;

  public Verificacao(String categoria, String resultado) {
    this.categoria = categoria;
    this.resultado = resultado;
  }

  public String getCategoria() {
    return categoria;
  }

  public String getResultado() {
    return resultado;
  }
}
