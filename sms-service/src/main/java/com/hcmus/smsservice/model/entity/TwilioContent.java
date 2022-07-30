package com.hcmus.smsservice.model.entity;

public class TwilioContent extends AbstractContent {

  @Override
  public String build() {
    return body;
  }
}
