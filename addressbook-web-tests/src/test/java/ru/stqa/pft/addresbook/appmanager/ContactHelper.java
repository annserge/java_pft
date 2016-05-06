package ru.stqa.pft.addresbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addresbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Anna on 19.04.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  //enter button (submit new contact)
  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePh());
    type(By.name("mobile"), contactData.getMobPh());
    type(By.name("work"), contactData.getWorkPh());
    type(By.name("email"), contactData.getMail1());
    type(By.name("email2"), contactData.getMail2());
    type(By.name("email3"), contactData.getMail3());

    if (creation) {
      //выбор элемента из списка
      //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      //"заглушка" против отсутствия групп в базе при создании контактов:
      Assert.assertTrue(isElementPresent(By.name("new_group")));
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void modify(ContactData contact) {
    editSelectedContactById(contact.getId());
    fillContactForm(contact, false);
    submitContactUpdate();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  //pencil image by id
  public void editSelectedContactById (int id) {
    WebElement checkbox = wd.findElement(By.id("" + id));
    checkbox.findElement(By.xpath("./../../td[8]/a")).click();
  }

  //set checkbox by id
  public void selectContactById (int id) {
    wd.findElement(By.id("" + id)).click();
  }

  //delete button
  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  //pencil image
  /*public void editSelectedContact(int index) {
    String currXpath="//table[@id='maintable']/tbody/tr[" + (index+2) +"]/td[8]/a/img";
    wd.findElement(By.xpath(currXpath)).click();
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }*/

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {
      String lastName  = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    }
    return contacts;
  }
}
