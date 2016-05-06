package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

import java.util.Set;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //если нет контакта, то создать его
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstName("Anna"));
      app.goTo().homePage();
    }
  }

  @Test//(enabled = false)
  public void testContactModificaion() {
    Set<ContactData> before = app.contact().all();
    //выбор случайного контакта для модификции:
    ContactData modifiedContact = before.iterator().next();
    //group=null - потому что при модификции контакта поле group не доступно для заполнения, значит, оставляем его без изменений:
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Anna").withLastName("Sergeeva")
            .withAddress("My address is somewhere").withHomePh("(h)1234567").withMobPh("(m)1234567").withWorkPh("(w)1234567")
            .withMail1("anna.sergeeva@server.com").withMail2("mail2").withMail3("mail3");
    app.contact().modify(contact);
    app.goTo().homePage();

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
