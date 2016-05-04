package ru.stqa.pft.addresbook.model;

public class ContactData {
  private int id;
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String homePh;
  private final String mobPh;
  private final String workPh;
  private final String mail1;
  private final String mail2;
  private final String mail3;
  private String group;

  public ContactData(int id, String firstName, String lastName, String address, String homePh, String mobPh, String workPh, String mail1, String mail2, String mail3, String group ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.homePh = homePh;
    this.mobPh = mobPh;
    this.workPh = workPh;
    this.mail1 = mail1;
    this.mail2 = mail2;
    this.mail3 = mail3;
    this.group = group;
  }

  public ContactData(String firstName, String lastName, String address, String homePh, String mobPh, String workPh, String mail1, String mail2, String mail3, String group ) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.homePh = homePh;
    this.mobPh = mobPh;
    this.workPh = workPh;
    this.mail1 = mail1;
    this.mail2 = mail2;
    this.mail3 = mail3;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePh() {
    return homePh;
  }

  public String getMobPh() {
    return mobPh;
  }

  public String getWorkPh() {
    return workPh;
  }

  public String getMail1() {
    return mail1;
  }

  public String getMail2() {
    return mail2;
  }

  public String getMail3() {
    return mail3;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}

