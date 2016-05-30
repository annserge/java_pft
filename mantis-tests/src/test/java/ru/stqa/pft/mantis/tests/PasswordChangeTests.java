package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.BtUserData;
import ru.stqa.pft.mantis.model.BtUsers;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

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
    String user = "user";
    String password = "123";
    assertTrue(app.newSession().login(admin, admin_password));
    //если криденшелы валидные, можно активировать ресурсоемкий браузер:
 ////   app.user().login(admin, admin_password);

    BtUsers allUsers = app.db().btUsers();
    //BtUserData modUser = allUsers.iterator().next();
    BtUserData ggg = app.db().btUsers().iterator().next();
    System.out.println(ggg.getName());

    BtUserData modUser = app.db().btUsers().stream().filter((u) -> u.getName().equals("Anna")).collect(Collectors.toList()).iterator().next();
    //BtUserData modUser = app.db().btUsers().iterator().next();
    System.out.println(modUser);
            //BtUsers allUsers = app.db().btUsers().stream().filter((u) -> )
    //BtUserData modifiedUser = app.db().btUsers().stream().filter((u) -> u.withName("Anna")); //withName()equals()).iterator().next();
  //  BtUserData modifiedUser = app.db().btUsers().stream().filter((u) -> ! u.getName().equals("administrator")).iterator().next(); //withName()equals()).iterator().next();

   // System.out.println(modifiedUser.getName());
    //System.out.println(app.db().btUsers().size());


//    app.user().resetPassFor();



    //app.newSession().login(admin, admin_password)
    //adminResetPass();
    //userChangePass();
    assertTrue(app.newSession().login(admin, admin_password));
    //assertTrue(app.newSession().login(user, password));
  }

  @Test(enabled = false)
  public void testMyPassword(){
    int d = app.db().btUsers().size();
    System.out.println("*******************");
    System.out.println(d);
    //System.out.println(app.db().btUsers());
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
