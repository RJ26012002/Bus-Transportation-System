package com.example.bst.documents;
public class Profile {
  private String id;
  private int number, password;
  private Bus bus;
  private int type;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public int getPassword() {
    return password;
  }

  public void setPassword(int password) {
    this.password = password;
  }

  public Bus getBus() {
    return bus;
  }

  public void setBus(Bus bus) {
    this.bus = bus;
  }

}


