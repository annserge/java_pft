package ru.stqa.pft.sandbox;

/**
 * Created by Anna on 08.04.2016.
 */
/* --for point 3
public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
}
*/

public class Point {

  public double distance( double x1, double y1, double x2, double y2) {
    System.out.println("-----\nКоординаты точки 1 [x : y] >> " + x1 + " : " + y1);
    System.out.println("Координаты точки 2 [x : y] >> " + x2 + " : " + y2);
    double res = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

    System.out.println("Расстояние между точками   >> " + res);
    return res;
  }
}
