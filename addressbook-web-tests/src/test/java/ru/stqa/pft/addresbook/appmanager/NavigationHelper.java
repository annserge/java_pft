package ru.stqa.pft.addresbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Anna on 18.04.2016.
 */
public class NavigationHelper extends HelperBase {
  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new")) ) {
      return;
    }
    click(By.linkText("groups"));
    //переход по линку groups, только пока не: найден заголовок с тегом h1 и именем Groups плюс кнопка создания группы с имененм new
  }

  public void gotoAddNewPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))) {return;}
    click(By.linkText("add new"));
    //переход по линку add new(contact), только пока не: найден заголовок с тегом h1 и именем "Edit / add address book entry"
    //плюс кнопка создания контакта с именем submit
  }

  public void returnToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }
}
