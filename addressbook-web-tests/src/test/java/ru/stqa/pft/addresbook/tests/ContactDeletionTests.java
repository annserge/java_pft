package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

import java.util.Set;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    //выбор случайного контакта для удаления:
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().confirmAndHome();

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}