package ru.stqa.pft.addresbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.GroupData;

/**
 * Created by Anna on 18.04.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification () {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();

    //если нет группы, то создать ее
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
