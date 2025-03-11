package com.safescore.sql;
import net.datafaker.Faker;

public class DatabaseSeeder {
  public static void test(){
    Faker faker = new Faker();

    // Gerar dados fictícios
    String nome = faker.name().fullName();
    String cidade = faker.address().fullAddress();
    String email = faker.internet().emailAddress();
    String test = faker.dragonBall().character();


    System.out.println("Nome: " + nome);
    System.out.println("Cidade: " + cidade);
    System.out.println("Email: " + email);
    System.out.println("Test: " + test);

  }

  public static void main(String[] args) {
    test();
  }
}

