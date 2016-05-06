package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //если нет контакта, то создать его
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData("Anna", null, null, null, null, null, null, null, null, null));
      app.goTo().homePage();
    }
  }

  @Test//(enabled = false)
  public void testContactModificaion() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    //null - потому что при модификции контакта поле group не доступно для заполнения, значит, оставляем его без изменений:
    ContactData contact = new ContactData(before.get(index).getId(), "Anna", "Sergeeva", "My address is somewhere", "(h)1234567", "(m)1234567", "(w)1234567", "anna.sergeeva@server.com", "mail2", "mail3", null);

    app.getContactHelper().modifyContact(index, contact);
    app.goTo().homePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
