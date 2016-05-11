package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Anna on 11.05.2016.
 */
public class ContactDetailsTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    //если нет контакта, то создать его
    if (app.contact().all().size() == 0) {
      app.goTo().addNewPage();
      app.contact().create(new ContactData().withFirstName("Anna").withLastName("Sergeeva").withHomePh("+7(123)45-67").withMobPh("").withWorkPh("123 45 97").withAddress("This\nis my\n\naddress").withMail2("anna@sergeeva.com").withMail3("test mail"));
      app.goTo().homePage();
    }
  }

  @Test//(enabled = false)
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoFromDetailsPage = app.contact().infoFromDetailsPage(contact);

    assertThat((cleanedPhones(cleanedMails(withoutEmptyLines(contactInfoFromDetailsPage)))), equalTo(withoutEmptyLines(mergeContactInfoFromEditForm(contactInfoFromEditForm))));
    int f=0;
  }

  private String mergeContactInfoFromEditForm(ContactData contact) {
    String mergedName = Arrays.asList(contact.getFirstName(), contact.getLastName())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining(" "));

    String mergedAddress = Arrays.asList(contact.getAddress())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));

    String mergedPhones = Arrays.asList(cleanedPhones(contact.getHomePh()), contact.getMobPh(), contact.getWorkPh())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));

    return Arrays.asList(mergedName, mergedAddress, mergedPhones,
            contact.getMail1(), contact.getMail2(), contact.getMail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedPhones(String s) {
    return s.replaceAll("\n[HWM]: ", "\n");
  }
  public static String cleanedMails(String s) {
    return s.replaceAll(" \\(w{3}.*\\)", "");
  }

  public static String withoutEmptyLines(String s) {
    return s.replaceAll("\n\n", "\n");
  }
}
