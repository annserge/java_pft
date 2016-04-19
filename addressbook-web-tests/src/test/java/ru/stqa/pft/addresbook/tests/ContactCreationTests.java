package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addresbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().gotoAddNewPage();
   /* app.getGroupHelper().fillContactForm(new ContactData("Anna", "Sergeeva", "My address is somewhere", "(h)1234567", "(m)1234567", "(w)1234567", "anna.sergeeva@server.com", "mail2", "mail3"));
    app.getGroupHelper().submitContactCreation();
    app.getGroupHelper().returnToHomePage();
  */}

}
