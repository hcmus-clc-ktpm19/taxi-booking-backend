package com.hcmus.wiberback.model.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
  private final String id;

  public UserNotFoundException(String message, String id) {
    super(message);
    this.id = id;
  }
}
