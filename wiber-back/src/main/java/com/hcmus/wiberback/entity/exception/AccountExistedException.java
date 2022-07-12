package com.hcmus.wiberback.entity.exception;

import com.hcmus.wiberback.entity.enums.Role;
import lombok.Getter;

@Getter
public class AccountExistedException extends RuntimeException {

  private final String phone;
  private final Role role;

  public AccountExistedException(String message, String phone, Role role) {
    super(message);
    this.phone = phone;
    this.role = role;
  }
}
