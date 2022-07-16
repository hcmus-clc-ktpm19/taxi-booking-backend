package com.hcmus.wiberback.model.exception;

public class UserNotFoundException extends RuntimeException {
  private String id;

  public UserNotFoundException(String message, String id) {
    super(message);
    this.id = id;
  }
}
