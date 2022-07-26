package com.hcmus.smsservice.util;

import com.hcmus.smsservice.model.dto.CarRequestDto;
import com.hcmus.smsservice.model.entity.AbstractContent;
import com.hcmus.smsservice.model.entity.SendGridContent;
import com.hcmus.smsservice.model.enums.SenderServiceType;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SenderFacade {

  private final SenderFactory senderFactory;

  public void send(CarRequestDto carRequestDto, SenderServiceType serviceType) throws IOException {
    AbstractContent content = null;

    switch (serviceType) {
      case SENDGRID:
        content = SendGridContent
            .builder()
            .from("hcmus.test.1@gmail.com")
            .subject("Car Request")
            .to("hcmus.test.1@gmail.com")
            .content("hehe")
            .build();

        break;
      case TWILIO:
        break;
      default:
        throw new IllegalArgumentException("Unknown service type: " + serviceType);
    }

    senderFactory.getSenderService(serviceType).send(content);
  }
}