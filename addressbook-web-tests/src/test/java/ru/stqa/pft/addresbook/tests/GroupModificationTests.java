package ru.stqa.pft.addresbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.GroupData;

/**
 * Created by Anna on 18.04.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification () {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
