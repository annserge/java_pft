package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Anna on 14.04.2016.
 */
public class SquareTests {

  @Test
  public void testArea() {
    Square s5 = new Square(5);
    Assert.assertEquals(s5.area(), 20.0);
    Square s12 = new Square(12);
    Assert.assertEquals(s12.area(), 144.0);
    Square s0 = new Square(0);
    Assert.assertEquals(s0.area(), 0.0);
  }
}
