package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * Created by Anna on 18.04.2016.
 */
public class ApplicationManager {

  private final Properties properties;
  //чтобы wd был доступен только из данного класса, вместе с инициализацией при вызове метода getDriver:
  private WebDriver wd;

  private String browser;
  private RegistrationHelper registrationHelper;

  public ApplicationManager(String browser){
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    //останавливать нужно браузер, только если он был инициализирован:
    if (wd != null) {
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    //"ленивая" инициализация, то есть, только при необходимости:
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public WebDriver getDriver() {
    //"ленивая" инициализация, то есть, только при необходимости:
    if (wd == null) {
      if (Objects.equals(browser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (Objects.equals(browser, BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (Objects.equals(browser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }
}
