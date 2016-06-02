package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by Anna on 02.06.2016.
 */
public class BTRelatedTests extends TestBase{

  @Test
  public void testForIssue1() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(1); //issue#1 is in status new - test should be ignored
    System.out.println("\nFORMAL TEST 1");
  }

  @Test
  public void testForIssue2() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(2); //test#2 is in status resolved - test should be run
    System.out.println("\nFORMAL TEST 2");
  }
}
