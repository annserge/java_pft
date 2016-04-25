package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificaion() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().editSelectedContact();
    app.getContactHelper().fillContactForm(new ContactData("111", "222", "333", "4", "5", "6", "7", "8", "9", null));
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().returnToHomePage();
  }
}
