package com.example.springboot;

import java.io.Serializable;

public class TokenInfo implements Serializable {

  private String id;
  private String typeUser;

  public TokenInfo() {
  }

  public TokenInfo(String id, String typeUser) {
    this.id = id;
    this.typeUser = typeUser;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTypeUser() {
    return typeUser;
  }

  public void setTypeUser(String typeUser) {
    this.typeUser = typeUser;
  }
}
