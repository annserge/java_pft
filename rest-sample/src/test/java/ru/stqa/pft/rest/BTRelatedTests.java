package ru.stqa.pft.rest;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Anna on 03.06.2016.
 */
public class BTRelatedTests extends TestBase {

  @Test
  public void testForIssue1() throws IOException {
    skipIfNotFixed(1); //issue#1 is in status new - test should be run
    System.out.println("\nFORMAL TEST 1");
  }

  @Test
  public void testForIssueResolved() throws IOException {
    skipIfNotFixed(4); //test#4 is in status resolved - test should be ignored
    System.out.println("\nFORMAL TEST 4");
  }

  @Test
  public void testForIssueClosed() throws IOException {
    skipIfNotFixed(8); //test#8 is in status resolved - test should be ignored
    System.out.println("\nFORMAL TEST 8");
  }
}
