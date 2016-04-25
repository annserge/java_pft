package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().gotoAddNewPage();
    //app.getContactHelper().fillContactForm(new ContactData("Anna", "Sergeeva", "My address is somewhere", "(h)1234567", "(m)1234567", "(w)1234567", "anna.sergeeva@server.com", "mail2", "mail3", "test1"), true);
    app.getContactHelper().fillContactForm(new ContactData("Anna", null, null, null, null, null, null, null, null, "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }
}
