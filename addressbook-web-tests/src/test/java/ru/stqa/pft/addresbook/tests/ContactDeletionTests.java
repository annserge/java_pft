package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getNavigationHelper().confirmAlert();
    app.getNavigationHelper().returnToHomePage();
  }
}