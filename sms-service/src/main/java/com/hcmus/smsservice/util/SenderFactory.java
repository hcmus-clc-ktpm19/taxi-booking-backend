package com.hcmus.smsservice.util;

import com.hcmus.smsservice.model.enums.SenderServiceType;
import com.hcmus.smsservice.service.SenderService;
import com.hcmus.smsservice.service.SendGridService;
import com.hcmus.smsservice.service.TwilioService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SenderFactory {

  private final SendGridService sendGridService;
  private final TwilioService twilioService;

  public SenderService getSenderService(SenderServiceType senderServiceType) {
    Map<SenderServiceType, SenderService> senderServiceMap = Map.of(
        SenderServiceType.SENDGRID, sendGridService,
        SenderServiceType.TWILIO, twilioService
    );

    return senderServiceMap.get(senderServiceType);
  }
}