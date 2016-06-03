package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import javax.json.JsonObject;
import java.io.IOException;

/**
 * Created by Anna on 03.06.2016.
 */
public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("2afa21d224ff8c254deeb74d0ac10fffb05a3d92");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("annserge", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
