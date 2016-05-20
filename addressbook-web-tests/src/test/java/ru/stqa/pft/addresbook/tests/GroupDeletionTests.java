package ru.stqa.pft.addresbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addresbook.model.GroupData;
import ru.stqa.pft.addresbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      /*если нет группы, то создать ее*/
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test//(enabled = false)
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    //выбор случайной группы для удаления:
    GroupData deletedGroup= before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, CoreMatchers.equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();
  }
}
