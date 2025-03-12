package com.safescore.sql;
import net.datafaker.Faker;
import  java.util.*;
import java.util.*;

public class DatabaseSeeder {
  public static void test(){
    Faker faker = new Faker(Locale.of("pt", "BR"));

    //usuario
    System.out.println("CPF: " + faker.number().digits(11));
    System.out.println("Nome: " + faker.name().name());
    System.out.println("Niver: "+ faker.date().birthday(18,110));
    System.out.println("Dependentes: "+ faker.number().numberBetween(0,4));//4 representa 4 ou mais

    //escolaridade
    String[] escolaridades = {
            "Sem Escolaridade",
            "Ensino Fundamental Incompleto",
            "Ensino Fundamental Completo",
            "Ensino Médio Incompleto",
            "Ensino Médio Completo",
            "Técnico",
            "Superior Incompleto",
            "Superior Completo",
            "Pós-graduação",
            "Mestrado",
            "Doutorado",
            "Pós-doutorado"
    };






    faker.number().numberBetween(0,(escolaridades.length - 1))]
    System.out.println(escolaridades[faker.number().numberBetween(0,(escolaridades.length - 1))]);
    System.out.println();
    System.out.println("Cidade: " + faker.address().fullAddress());
    System.out.println("Email: " + faker.internet().emailAddress());
    System.out.println("Personagem DragonBall: " + faker.dragonBall().character());
    System.out.println(faker.country().name());
    System.out.println(faker.address().city());
    System.out.println(faker.basketball().players());
    System.out.println(faker.address().zipCode());


    calculadorChance(escolaridades.length,4);


  }




  public static double[] calculadorChance(int quantidadeOpcoes, int maiorChance){
    double[] chances = new double[quantidadeOpcoes];
    for (int i = 0; i < chances.length; i++) {
      double distancia = Math.abs(i - maiorChance);
      chances[i] = 1 - (distancia/10);
    }
    return chances;
  }

  public static void main(String[] args) {
    test();
  }
}

