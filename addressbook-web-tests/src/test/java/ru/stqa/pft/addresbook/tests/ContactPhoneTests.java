package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 11.05.2016.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //если нет контакта, то создать его
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstName("Anna").withHomePh("111").withMobPh("222").withWorkPh("333"));
      app.goTo().homePage();
    }
  }

  @Test//(enabled = false)
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePh(), equalTo(cleaned(contactInfoFromEditForm.getHomePh())));
    assertThat(contact.getMobPh(),  equalTo(cleaned(contactInfoFromEditForm.getMobPh())));
    assertThat(contact.getWorkPh(), equalTo(cleaned(contactInfoFromEditForm.getWorkPh())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
