package ru.stqa.pft.addresbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addresbook.model.ContactData;
import ru.stqa.pft.addresbook.model.Contacts;

import java.util.List;

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
    contactCache = null;
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
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      //возвр копию на всякий случай, чтобы кто-нибудь не испортил кэш:
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName  = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address   = cells.get(3).getText();
      String allMails  = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAllPhones(allPhones).withAllMails(allMails).withAddress(address));
    }
    //тоже возвр копию
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    //read person names:
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname  = wd.findElement(By.name("lastname")).getAttribute("value");
    //read phones:
    String home      = wd.findElement(By.name("home")).getAttribute("value");
    String mobile    = wd.findElement(By.name("mobile")).getAttribute("value");
    String work      = wd.findElement(By.name("work")).getAttribute("value");
    //read address:
    String address   = wd.findElement(By.name("address")).getAttribute("value");
    //read e-mails:
    String email1    = wd.findElement(By.name("email")).getAttribute("value");
    String email2    = wd.findElement(By.name("email2")).getAttribute("value");
    String email3    = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePh(home).withMobPh(mobile).withWorkPh(work)
            .withAddress(address)
            .withMail1(email1).withMail2(email2).withMail3(email3);
  }

  private void initContactModificationById(int id) {
/*    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
*/
//    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
}
