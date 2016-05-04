package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();

    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData("Anna", null, null, null, null, null, null, null, null, null));
    //app.getContactHelper().createContact(new ContactData("Anna", "Sergeeva", "My address is somewhere", "(h)1234567", "(m)1234567", "(w)1234567", "anna.sergeeva@server.com", "mail2", "mail3", "test1"), true);
    app.getNavigationHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
    System.out.println(before + " - " + after);
  }
}
