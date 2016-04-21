package ru.stqa.pft.sandbox;

/**
 * Created by Anna on 21.04.2016.
 */
public class Equation {

  private double a;
  private double b;
  private double c;

  private int n;

  public Equation(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;

    double d = b * b - 4 * a * c;

    if (a != 0) {
      if (d > 0) {
        n = 2;
      } else if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }

    } else if (b != 0) {
      n = 1;
      System.out.println("Это вырожденное уравнение");

    } else if (c != 0) {
      n = 0;
      System.out.println("Это дважды вырожденное уравнение");

    } else {
      n = -1;
      System.out.println("Бесконечное множество решений");
    }
  }

  public int rootNumber() {
    return n;
  }
}
