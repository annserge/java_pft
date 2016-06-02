package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Anna on 02.06.2016.
 */
public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Issue getIssue(int issueId, String adminL, String adminP, String uRL) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect(uRL);
    IssueData issueData = mc.mc_issue_get(adminL, adminP, BigInteger.valueOf(issueId));
    return new Issue().withId(issueData.getId().intValue())
            .withStatus(issueData.getStatus())
            .withSummary(issueData.getSummary()).withDescription(issueData.getDescription())
            .withProject(new Project().withId(issueData.getProject().getId().intValue())
                    .withName(issueData.getProject().getName()));
  }


  public Set<Project> getProjects(String adminL, String adminP, String uRL) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect(uRL);
    ProjectData[] projects = mc.mc_projects_get_user_accessible(adminL, adminP);
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect(String uRL) throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
              .getMantisConnectPort(new URL(uRL));
  }

  public Issue addIssue(Issue issue, String adminL, String adminP, String uRL) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect(uRL);
    String[] categories = mc.mc_project_get_categories(adminL, adminP, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(adminL, adminP, issueData);
    IssueData createdIssueData = mc.mc_issue_get(adminL, adminP, issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                    .withName(createdIssueData.getProject().getName()));
  }

}
