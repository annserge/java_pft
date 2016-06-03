package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Anna on 03.06.2016.
 */
public class TestBase {

  public boolean isIssueOpen(int issueId) throws IOException {

    /*
      которая должна через Remote API получать из баг-трекера информацию о баг-репорте
      с заданным идентификатором, и возвращать значение false или true в зависимости от того,
      помечен он как исправленный или нет.
     */
    Set<Issue> issues = getIssues(issueId);
    Issue issue = issues.iterator().next();
    boolean isIssueOpen = true;
    if (issue.getStatus().equals("Resolved") || issue.getStatus().equals("Closed")) {isIssueOpen = false;
      System.out.println("\nIgnored because of issue " + issueId +" "+issue.getStatus());}
    return isIssueOpen;
  }



  private Set<Issue> getIssues(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (! isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
/*
"state_name" -> ""Closed""

{"assignee_id":0,"category_id":1,"created":"2016-06-03T06:00:04+00:00",
"creator":{"id":"1","created":"2016-06-03T06:00:04+00:00","updated":"2016-06-03T06:00:04+00:00",
"firstname":"Demo","lastname":"User","name":"Demo User","email":"demo@bugify.com",
"username":"demo",
"notifications":{"creator":true,"assignee":true,"following":true,"commented":true,"mychange":true,
"mentioned":true,"allcreates":true},"groups":[],"settings":[],"owner":true,"timezone":"UTC","state":1,
"api_key":"LSGjeU4yP1X493ud1hNniA=="},"creator_id":1,"description":"Sample issue description","id":1,
"labels":[],"milestone":{"id":"1","created":"2016-06-03T06:00:04+00:00",
"updated":"2016-06-03T06:00:04+00:00","archived":"","due":"2016-08-02T06:00:04+00:00",
"days_remaining":60,"name":"Blackbird v1","description":"","state":"1"},"milestone_id":1,
"percentage":0,"priority":"1","priority_name":"Normal","project":{"id":"1",
"created":"2016-06-03T06:00:03+00:00","updated":"2016-06-03T06:00:03+00:00","name":"Blackbird",
"slug":"blackbird","categories":[],"description":"","state":"1"},"project_id":1,"related_issue_ids":[2],
"resolved":"1970-01-01T00:00:00+00:00","state":"0","state_name":"Open","subject":"Sample issue 1",
"updated":"2016-06-03T06:04:56+00:00"}
 */