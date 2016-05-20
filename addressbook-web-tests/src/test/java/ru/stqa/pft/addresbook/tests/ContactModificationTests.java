package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      //если нет контакта, то создать его
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstName("Anna"));
      app.goTo().homePage();
    }
  }

  @Test//(enabled = false)
  public void testContactModificaion() {
    Contacts before = app.db().contacts();
    //выбор случайного контакта для модификции:
    ContactData modifiedContact = before.iterator().next();
    //group=null - потому что при модификции контакта поле group не доступно для заполнения, значит, оставляем его без изменений:
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("Anna").withLastName("Sergeeva")
            .withAddress("My address is somewhere\n\nhere").withHomePh("(h)1234567").withMobPh("m-1234567").withWorkPh("w 1234567")
            .withMail1("anna.sergeeva@server.com").withMail2("mail2").withMail3("mail3");
    app.contact().modify(contact);
    app.goTo().homePage();

    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
