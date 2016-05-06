package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

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
    if (app.contact().list().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstName("Anna"));
      app.goTo().homePage();
    }
  }

  @Test//(enabled = false)
  public void testContactModificaion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    //group=null - потому что при модификции контакта поле group не доступно для заполнения, значит, оставляем его без изменений:
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstName("Anna").withLastName("Sergeeva")
            .withAddress("My address is somewhere").withHomePh("(h)1234567").withMobPh("(m)1234567").withWorkPh("(w)1234567")
            .withMail1("anna.sergeeva@server.com").withMail2("mail2").withMail3("mail3");
    app.contact().modify(index, contact);
    app.goTo().homePage();

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
