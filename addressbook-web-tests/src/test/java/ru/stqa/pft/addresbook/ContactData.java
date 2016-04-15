package ru.stqa.pft.addresbook;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String homePh;
  private final String mobPh;
  private final String workPh;
  private final String mail1;
  private final String mail2;
  private final String mail3;

  public ContactData(String firstName, String lastName, String address, String homePh, String mobPh, String workPh, String mail1, String mail2, String mail3) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.homePh = homePh;
    this.mobPh = mobPh;
    this.workPh = workPh;
    this.mail1 = mail1;
    this.mail2 = mail2;
    this.mail3 = mail3;
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
}
