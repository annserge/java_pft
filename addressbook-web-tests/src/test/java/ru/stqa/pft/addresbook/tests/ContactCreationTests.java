package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test//(enabled = false)
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();

    app.goTo().addNewPage();
    ContactData contact = new ContactData().withFirstName("Anna888").withLastName("Sergeeva");
    //new ContactData("Anna", "Sergeeva", "My address is somewhere", "(h)1234567", "(m)1234567", "(w)1234567", "anna.sergeeva@server.com", "mail2", "mail3", "test1"), true);
    app.contact().create(contact);
    app.goTo().homePage();

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
