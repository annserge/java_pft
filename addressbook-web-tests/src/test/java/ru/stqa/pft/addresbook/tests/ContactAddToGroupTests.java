package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;
import ru.stqa.pft.addresbook.model.GroupData;
import ru.stqa.pft.addresbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 25.05.2016.
 */
public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      /*если нет группы, то создать ее*/
      app.group().create(new GroupData().withName("test1"));
      assertThat(app.db().groups().size(), equalTo(1));
    }

    if (app.db().contacts().size() == 0) {
      //если нет контакта, то создать его
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstName("Anna").withLastName("Serge"));
      assertThat(app.db().contacts().size(), equalTo(1));
    }
  }

  @Test
  public void testContactAddToGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    //выбор случайных контакта и группы для модификции привязки:
    ContactData modifiedContact = contacts.iterator().next();
    GroupData relatedGroup = groups.iterator().next();

    app.goTo().homePage();
    app.contact().selectContactById(modifiedContact.getId());
    app.contact().selectRelatedGroup(relatedGroup);
    app.contact().submitAddToGroup();
    app.goTo().homePage();


    contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getId() == modifiedContact.getId()) {modifiedContact = contact;break;}
    }
    assertThat(app.contact().isContactInGroup(modifiedContact, relatedGroup), equalTo(true));
  }
}
