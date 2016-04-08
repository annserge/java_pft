package ru.stqa.pft.sandbox;

/**
 * Created by Anna on 08.04.2016.
 */
/* --for point 3
public class Homework01 {
  public static void main(String[] args) {
    Point p1 = new Point(2,1);
    Point p2 = new Point(4,6);

    System.out.println("Координаты точки 1 [x : y] >> " + p1.x + " : " + p1.y);
    System.out.println("Координаты точки 2 [x : y] >> " + p2.x + " : " + p2.y);


    System.out.println("Расстояние между точками   >> " + distance(p1,p2));

  }

  public static double distance(Point p1, Point p2) {
    double res = Math.sqrt ( Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2) );
    return res;
  }
}
*/

public class Homework01 {
  public static void main(String[] args) {

    //задаем точки в формате x1 y1 x2 y2
    Point p1 = new Point();
    p1.distance(2,1,4,6);
    p1.distance(8,3,-9.6,0);
    p1.distance(150,-13.3,-6,66.2);

  }
}
