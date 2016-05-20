package ru.stqa.pft.addresbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Anna on 06.05.2016.
 */
public class Contacts extends ForwardingSet<ContactData> {
  
  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    //такой конструктор создает копию объекта, можно работать с объектом до и после изменения:
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  public Contacts(Collection<ContactData> contacts) {
    //такой конструктор создает копию объекта, можно работать с объектом до и после изменения:
    this.delegate = new HashSet<ContactData>(contacts);
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }
  
  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
