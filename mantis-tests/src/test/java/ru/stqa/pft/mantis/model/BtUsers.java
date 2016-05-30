package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anna on 30.05.2016.
 */

public class BtUsers extends ForwardingSet<BtUserData> {

  private Set<BtUserData> delegate;

  public BtUsers(BtUsers btUsers) {
    //такой конструктор создает копию объекта, можно работать с объектом до и после изменения:
    this.delegate = new HashSet<BtUserData>(btUsers.delegate);
  }

  public BtUsers() {
    this.delegate = new HashSet<BtUserData>();
  }

  @Override
  protected Set<BtUserData> delegate() {
    return null;
  }

  public BtUsers(Collection<BtUserData> btUsers) {
    //такой конструктор создает копию объекта, можно работать с объектом до и после изменения:
    this.delegate = new HashSet<BtUserData>(btUsers);
  }

}
