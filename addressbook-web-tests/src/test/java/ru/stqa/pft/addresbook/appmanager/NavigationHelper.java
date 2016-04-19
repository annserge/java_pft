package ru.stqa.pft.addresbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Anna on 18.04.2016.
 */
public class NavigationHelper extends HelperBase {
  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void gotoAddNewPage() { click(By.linkText("add new"));  }

  public void returnToHomePage() { click(By.linkText("home"));  }

}
