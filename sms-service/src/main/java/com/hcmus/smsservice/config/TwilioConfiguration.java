package com.hcmus.smsservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class TwilioConfiguration {

  @Value("${twilio.account.sid}")
  private String sid;

  @Value("${twilio.account.token}")
  private String token;

  @Value("${twilio.account.phone-number}")
  private String phoneNumber;
}
