package ru.stqa.pft.addresbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addresbook.model.ContactData;

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


  public void selectContact() {
    click(By.name("selected[]"));
  }

  //delete button
  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  //pencil image
  public void editSelectedContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactUpdate() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact, boolean create) {
    fillContactForm(contact, create);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
