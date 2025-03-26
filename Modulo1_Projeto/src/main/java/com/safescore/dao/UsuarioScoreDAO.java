package com.safescore.dao;

import com.safescore.dao.CrudMethods.Read;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class UsuarioScoreDAO {


  public static void main(String[] args) {
  estabilidadeEndereco("035.261.463-30");
  }

  public static void estabilidadeEndereco(String cpf) {
    int tempoEnderecoAtual = 0;

    Date dataInicialEndereco;
    List<Object[]> contratos = Read.listarContratosResidenciais(cpf);
    for (Object[] contratoResidencial : contratos){
      if(contratoResidencial[3] == null){
        dataInicialEndereco = new Date(((Date) contratoResidencial[2]).getTime());

        LocalDate dataInicialLocalDate = dataInicialEndereco.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataHoje = LocalDate.now();
        tempoEnderecoAtual = (int) ChronoUnit.DAYS.between(dataInicialLocalDate, dataHoje);

        break;
      }
    }
    int totalEnderecos = contratos.size();

    System.out.println(totalEnderecos);
    System.out.println(tempoEnderecoAtual);

    //taxaCaged

    //tipoContratoAtual
  }
}
