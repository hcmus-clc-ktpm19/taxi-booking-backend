package com.hcmus.smsservice.util;

import com.hcmus.smsservice.config.TwilioConfiguration;
import com.hcmus.smsservice.model.dto.CarRequestDto;
import com.hcmus.smsservice.model.entity.AbstractContent;
import com.hcmus.smsservice.model.entity.SendGridContent;
import com.hcmus.smsservice.model.entity.TwilioContent;
import com.hcmus.smsservice.model.enums.SenderServiceType;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SenderFacade {

  private final TwilioConfiguration twilioConfiguration;
  private final SenderFactory senderFactory;

  private AbstractContent generateSendGridContent(CarRequestDto carRequestDto) {
    SendGridContent content = new SendGridContent();
    content.setFrom("hcmus.test.1@gmail.com");
    content.setSubject("Car Request");
    content.setTo("hcmus.test.1@gmail.com");
    content.setBody(carRequestDto.getPickingAddress() + " hehe");

    return content;
  }

  private AbstractContent generateTwilioContent(CarRequestDto carRequestDto) {
    TwilioContent content = new TwilioContent();
    content.setFrom(twilioConfiguration.getPhoneNumber());
    content.setSubject("Car Request");
    content.setTo("+84835221101");
    content.setBody(carRequestDto.getPickingAddress() + " hehe");

    return content;
  }

  public void send(CarRequestDto carRequestDto, SenderServiceType serviceType) throws IOException {
    AbstractContent content;

    switch (serviceType) {
      case SENDGRID:
        content = generateSendGridContent(carRequestDto);
        break;
      case TWILIO:
        content = generateTwilioContent(carRequestDto);
        break;
      default:
        throw new IllegalArgumentException("Unknown service type: " + serviceType);
    }

    senderFactory.getSenderService(serviceType).send(content);
  }
}