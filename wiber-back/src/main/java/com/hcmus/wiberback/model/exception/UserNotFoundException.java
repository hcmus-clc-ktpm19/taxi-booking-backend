package com.hcmus.wiberback.model.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends AbstractNotFoundException {

  public UserNotFoundException(String message, String id) {
    super(message, id);
  }
}
