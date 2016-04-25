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
    //app.getContactHelper().fillContactForm(new ContactData("111", "222", "333", "4", "5", "6", "7", "8", "9", null), false);

    //null - потому что при модификции контакта поле group не доступно для заполнения, значит, оставляем его без изменений
    app.getContactHelper().fillContactForm(new ContactData("Anna", "Sergeeva", "My address is somewhere", "(h)1234567", "(m)1234567", "(w)1234567", "anna.sergeeva@server.com", "mail2", "mail3", null), false);
    app.getContactHelper().submitContactUpdate();
    app.getNavigationHelper().returnToHomePage();
  }
}
