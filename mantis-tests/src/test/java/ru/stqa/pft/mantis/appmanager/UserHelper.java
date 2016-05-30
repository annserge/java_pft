package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.BtUserData;

/**
 * Created by Anna on 30.05.2016.
 */
public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void resetPass(BtUserData user) {
    click(By.linkText("Manage"));
    click(By.linkText("Manage Users"));
    click(By.linkText(user.getName()));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void logout() {
    click(By.linkText("Logout"));
  }

  public void setNewPass(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
