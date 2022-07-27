package com.hcmus.smsservice.util;

import com.hcmus.smsservice.model.enums.SenderServiceType;
import com.hcmus.smsservice.service.SendGridService;
import com.hcmus.smsservice.service.SenderService;
import com.hcmus.smsservice.service.TwilioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SenderFactory {

  private final SendGridService sendGridService;
  private final TwilioService twilioService;

  public SenderService getSenderService(SenderServiceType senderServiceType) {
    switch (senderServiceType) {
      case SENDGRID:
        return sendGridService;
      case TWILIO:
        return twilioService;
      default:
        throw new IllegalArgumentException("Sender service type not supported");
    }
  }
}