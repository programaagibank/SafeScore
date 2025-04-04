package com.safescore.script;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.safescore.controller.UsuarioScoreController;
import com.safescore.model.Usuario;
import com.safescore.dao.CrudMethods.Read;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonGenerator {
  public static void main(String[] args) {
    String[] usuariosCpfs = Read.listarCpfs();
    Map<String, Object> dataset = new HashMap<>();
    dataset.put("training", new ArrayList<Map<String, Object>>());
    dataset.put("testing", new ArrayList<Map<String, Object>>());

    for (String usuarioCPF : usuariosCpfs) {
      Usuario usuario = UsuarioScoreController.definirUsuario(usuarioCPF);
      Map<String, Object> dadosUsuario = new HashMap<>();

      // Adicionando atributos normalizados
      dadosUsuario.put("rangeIdade", usuario.getRangeIdade());
      dadosUsuario.put("numeroDependentes", usuario.getNumeroDependentes());
      dadosUsuario.put("estadoCivil", usuario.getEstadoCivil());
      dadosUsuario.put("escolaridade", usuario.getEscolaridade());
      dadosUsuario.put("tempoEnderecoAnos", usuario.getTempoEnderecoAnos());
      dadosUsuario.put("nivelInadimplenciaEstado", usuario.getNivelInadimplenciaEstado());
      dadosUsuario.put("tipoContratoResidencia", usuario.getTipoContratoResidencia());
      dadosUsuario.put("tipoEmprego", usuario.getTipoEmprego());
      dadosUsuario.put("tempoEmpregoAtual", usuario.getTempoEmpregoAtual());
      dadosUsuario.put("salarioLiquidoMensal", usuario.getSalarioLiquidoMensal());
      dadosUsuario.put("montanteInvestimentos", usuario.getMontanteInvestimentos());
      dadosUsuario.put("montanteBens", usuario.getMontanteBens());
      dadosUsuario.put("saldo", usuario.getSaldo());
      dadosUsuario.put("restanteMensal", usuario.getRestanteMensal());
      dadosUsuario.put("estaInadimplente", usuario.isEstaInadimplente());
      dadosUsuario.put("valorParcelaAtiva", usuario.getValorParcelaAtiva());
      dadosUsuario.put("mesesAtrasado", usuario.getMesesAtrasado());
      dadosUsuario.put("valorCreditoRestanteTotal", usuario.getValorCreditoRestanteTotal());

      // Criando o objeto final que inclui CPF separadamente
      Map<String, Object> usuarioFinal = new HashMap<>();
      usuarioFinal.put("cpf", usuarioCPF);
      usuarioFinal.put("dados", dadosUsuario);

      // Definição do dataset (training ou testing)
      if (automaticCreditRisk.calcularRiscoCredito(usuario)) {
        dadosUsuario.put("seraInadimplente", true);
        ((List<Map<String, Object>>) dataset.get("training")).add(usuarioFinal);
      } else {
        ((List<Map<String, Object>>) dataset.get("testing")).add(usuarioFinal);
      }
    }

    // Criando JSON formatado
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try (FileWriter writer = new FileWriter("dados.json")) {
      gson.toJson(dataset, writer);
      System.out.println("Arquivo JSON gerado com sucesso!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
