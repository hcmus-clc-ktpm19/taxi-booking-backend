package com.hcmus.socketservice.model.entity;

import lombok.Data;
import lombok.NonNull;

/**
 * Message sent to server by browser
 */
@Data
public class Message {
  @NonNull
  private String message;
}
