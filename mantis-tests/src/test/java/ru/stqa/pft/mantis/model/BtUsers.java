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

  public BtUsers(Collection<BtUserData> users) {
    this.delegate = new HashSet<BtUserData>(users);
  }

  @Override
  protected Set<BtUserData> delegate() {
    return delegate;
  }
}