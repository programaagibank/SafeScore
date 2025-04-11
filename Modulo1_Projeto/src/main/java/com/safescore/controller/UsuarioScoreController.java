package com.safescore.controller;

import com.safescore.dao.UsuarioScoreDAO;
import com.safescore.model.Usuario;
import com.safescore.script.automaticCreditRisk;

import java.util.Arrays;

public class UsuarioScoreController {

  public static Usuario definirUsuario(String cpf) {
    try {
      Object[] informacoesPessoais = UsuarioScoreDAO.informacoesPessoais(cpf);
      Object[] estabilidadeEndereco = UsuarioScoreDAO.estabilidadeEndereco(cpf);
      Object[] empregoVinculoTrabalhista = UsuarioScoreDAO.empregoVinculoTrabalhista(cpf);
      Object[] historicoFinanceiro = UsuarioScoreDAO.historicoFinanceiro(cpf);

      return new Usuario(
              cpf,
              getString(informacoesPessoais, 0),
              getInt(informacoesPessoais, 1),
              getString(informacoesPessoais, 2),
              getString(informacoesPessoais, 3),

              getInt(estabilidadeEndereco, 0),
              getInt(estabilidadeEndereco, 1),
              getString(estabilidadeEndereco, 2),

              getString(empregoVinculoTrabalhista, 0),
              getInt(empregoVinculoTrabalhista, 1),
              getDouble(empregoVinculoTrabalhista, 2),

              getDouble(historicoFinanceiro, 0),
              getDouble(historicoFinanceiro, 1),
              getDouble(historicoFinanceiro, 2),
              getDouble(historicoFinanceiro, 3),
              getBoolean(historicoFinanceiro, 4),
              getDouble(historicoFinanceiro, 5),
              getInt(historicoFinanceiro, 6),
              getDouble(historicoFinanceiro, 7)
      );
    } catch (Exception e) {
      System.err.println("Error creating Usuario: " + e.getMessage());
      throw new RuntimeException("Failed to create Usuario", e);
    }
  }

  private static String getString(Object[] array, int index) {
    return array[index] != null ? array[index].toString() : "";
  }

  private static int getInt(Object[] array, int index) {
    if (array[index] == null) return 0;
    if (array[index] instanceof Number) return ((Number) array[index]).intValue();
    return Integer.parseInt(array[index].toString());
  }

  private static double getDouble(Object[] array, int index) {
    if (array[index] == null) return 0.0;
    if (array[index] instanceof Number) return ((Number) array[index]).doubleValue();
    return Double.parseDouble(array[index].toString());
  }

  private static boolean getBoolean(Object[] array, int index) {
    return array[index] != null && Boolean.parseBoolean(array[index].toString());
  }
}
