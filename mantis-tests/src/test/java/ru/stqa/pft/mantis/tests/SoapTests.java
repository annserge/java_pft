package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by Anna on 02.06.2016.
 */
public class SoapTests extends TestBase{

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    String adminL = app.getProperty("web.adminLogin");
    String adminP = app.getProperty("web.adminPass");
    String uRL = app.getProperty("web.mantisconnect");
    Set<Project> projects = app.soap().getProjects(adminL, adminP, uRL);

    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }
  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    String adminL = app.getProperty("web.adminLogin");
    String adminP = app.getProperty("web.adminPass");
    String uRL = app.getProperty("web.mantisconnect");
    Set<Project> projects = app.soap().getProjects(adminL, adminP, uRL);

    Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description")
            .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue, adminL, adminP, uRL);
    Assert.assertEquals(issue.getSummary(), created.getSummary());
  }
}
