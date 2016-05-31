package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Anna on 27.05.2016.
 */
public class RegistrationTests extends TestBase {

  //когда использую встроенный мейл-сервер:
  //@BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    //String user = "Anna";
    String password = "password";
    //String email = String.format("user%s@localhost.localdomain", now);
    String email = String.format("user%s@localhost", now);
    app.james().createUser(user, password);
    app.registration().start(user, email);

    //когда использую встроенный мейл-сервер:
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    //а вот так - для внешнего мейл-сервера:
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 120000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  //когда использую встроенный мейл-сервер:
  //@AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
