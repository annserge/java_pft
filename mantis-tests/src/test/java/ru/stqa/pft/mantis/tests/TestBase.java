package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by Anna on 18.04.2016.
 */
public class TestBase {


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

  public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {

    /*
    которая должна через Remote API получать из баг-трекера информацию о баг-репорте
    с заданным идентификатором, и возвращать значение false или true в зависимости от того,
    помечен он как исправленный или нет.
     */
    String adminL = app.getProperty("web.adminLogin");
    String adminP = app.getProperty("web.adminPass");
    String uRL = "http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php/mc_issue_get";//app.getProperty("web.mantisconnect");
    Issue issue = app.soap().getIssue(issueId, adminL, adminP, uRL);
    boolean isIssueOpen = true;
    if (issue.getStatus().getName().equals("resolved")) {isIssueOpen = false;}
    //if (issue.getStatus().equals("resolved")) {isIssueOpen = false;}
    return isIssueOpen;
  }

  public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
