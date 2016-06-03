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