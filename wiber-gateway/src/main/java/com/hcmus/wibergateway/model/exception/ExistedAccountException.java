package com.hcmus.wibergateway.model.exception;

import lombok.Getter;

@Getter
public class ExistedAccountException extends RuntimeException {

  private final String phone;

  public ExistedAccountException(String message, String phone) {
    super(message);
    this.phone = phone;
  }
}
