package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getNavigationHelper().gotoAddNewPage();
    ContactData contact = new ContactData("Anna", "Sergeeva", null, null, null, null, null, null, null, null);
    //new ContactData("Anna", "Sergeeva", "My address is somewhere", "(h)1234567", "(m)1234567", "(w)1234567", "anna.sergeeva@server.com", "mail2", "mail3", "test1"), true);
    app.getContactHelper().createContact(contact);
    app.getNavigationHelper().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (ContactData c : after) {
      if (c.getId() > max) {max = c.getId();}
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
