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
public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    //выбор случайного контакта для удаления:
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().confirmAndHome();

    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactsListInUI();
  }
}