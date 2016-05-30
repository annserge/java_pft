package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.BtUserData;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Anna on 30.05.2016.
 */
public class PasswordChangeTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test//(enabled = false)
  public void testPasswordChange() throws IOException, MessagingException {
    String admin = "administrator";
    String admin_password = "root";
    String password = "12345";

    assertTrue(app.newSession().login(admin, admin_password));
    //если криденшелы валидные, можно активировать ресурсоемкий браузер:
    app.user().login(admin, admin_password);
    //выбор жертвы, это не админ:
    BtUserData modifiedUser = app.db().btUsers().stream().filter((u) -> ! u.getName().equals("administrator")).iterator().next();
    app.user().resetPass(modifiedUser);
    app.user().logout();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, modifiedUser.getEmail());
    app.user().setNewPass(confirmationLink, password);

    assertTrue(app.newSession().login(modifiedUser.getName(), password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
