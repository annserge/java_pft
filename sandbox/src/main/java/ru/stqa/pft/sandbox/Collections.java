package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anna on 29.04.2016.
 */
public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java","C#", "Python","PHP"};

    List<String> languages = Arrays.asList("Java","C#", "Python","PHP");
    //List<String> languages = Arrays.asList(langs);

    /*for (int i=langs.length; i >0; ) {
      System.out.println("Я хочу выучить " + langs[--i]);
    }
    */
    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }
  }
}
