package com.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SupplierDto {
  
  @NotEmpty(message = "Name is mandatory")
  private String name;

  @NotEmpty(message = "Address is mandatory")
  private String address;

  @NotEmpty(message = "Email is mandatory")
  @Email(message = "Email is not valid")
  private String email;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  
  

}
