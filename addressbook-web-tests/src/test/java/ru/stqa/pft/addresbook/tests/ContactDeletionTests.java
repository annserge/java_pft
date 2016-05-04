package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();

    //если нет контакта, то создать его
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData("Anna", null, null, null, null, null, null, null, null, null));
      app.getNavigationHelper().returnToHomePage();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getNavigationHelper().confirmAlert();
    app.getNavigationHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
    System.out.println(before + " - " + after);
  }
}