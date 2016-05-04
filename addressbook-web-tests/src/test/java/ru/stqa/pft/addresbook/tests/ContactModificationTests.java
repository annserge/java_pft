package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificaion() {
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();

    //если нет контакта, то создать его
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData("Anna", null, null, null, null, null, null, null, null, null));
      app.getNavigationHelper().returnToHomePage();
    }
    app.getContactHelper().editSelectedContact(before -1);
    //null - потому что при модификции контакта поле group не доступно для заполнения, значит, оставляем его без изменений
    app.getContactHelper().fillContactForm(new ContactData("Anna", "Sergeeva", "My address is somewhere", "(h)1234567", "(m)1234567", "(w)1234567", "anna.sergeeva@server.com", "mail2", "mail3", null), false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
    System.out.println(before + " - " + after);
  }
}
