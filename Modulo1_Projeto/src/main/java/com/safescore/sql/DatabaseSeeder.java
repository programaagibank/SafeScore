package com.safescore.sql;
import net.datafaker.Faker;
import java.util.*;

public class DatabaseSeeder {
  public static void test(){
    Faker faker = new Faker(Locale.of("pt","BR"));

    //usuario
    System.out.println(faker.name().name());
    System.out.println(faker.date().birthday(18,100));
    System.out.println(faker.number().numberBetween(0,5));



  }

  public static void main(String[] args) {
    test();
  }
}

