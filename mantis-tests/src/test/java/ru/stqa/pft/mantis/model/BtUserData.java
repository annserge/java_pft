package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;

/**
 * Created by Anna on 30.05.2016.
 */


@Entity
@Table(name = "mantis_user_table")
public class BtUserData {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "username")
  private String username;
  //private String email;

  //private BtUsers

  public BtUserData withId(int id) {
    this.id = id;
    return this;
  }

  public BtUserData withName(String username) {
    this.username = username;
    return this;
  }

  public String getName() {
    return username;
  }
}
