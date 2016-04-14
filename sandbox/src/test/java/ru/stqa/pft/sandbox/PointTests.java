package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Anna on 14.04.2016.
 */
public class PointTests {

  @Test
  public void testDestination() {
    Point p1 = new Point();
    Assert.assertEquals(p1.distance(2,1,4,6), 5.385164807134504);
    Assert.assertEquals(p1.distance(8,3,-9.6,0), 17.85385112517745);
    Assert.assertEquals(p1.distance(150,-13.3,-6,66.2), 175.0892629489313);
    Assert.assertEquals(p1.distance(0,0,0,0), 0.0);
  }
}
