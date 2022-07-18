package com.hcmus.wiberback.model.exception;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {

  private final String phone;

  public AccountNotFoundException(String message, String phone) {
    super(message);
    this.phone = phone;
  }
}
