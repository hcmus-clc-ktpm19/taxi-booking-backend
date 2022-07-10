package com.hcmus.wiberback.entity.exception;

import lombok.Getter;

@Getter
public class AccountExistedException extends RuntimeException {

  private final String phone;

  public AccountExistedException(String message, String phone) {
    super(message);
    this.phone = phone;
  }
}
