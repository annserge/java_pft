package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

import java.util.List;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test(enabled = false)
  public void testContactDeletion() {
    app.goTo().returnToHomePage();
    //если нет контакта, то создать его
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData("Anna", null, null, null, null, null, null, null, null, null));
      app.goTo().returnToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.goTo().confirmAlert();
    app.goTo().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}