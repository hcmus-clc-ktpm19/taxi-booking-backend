package com.hcmus.smsservice.util;

import com.hcmus.smsservice.config.TwilioConfiguration;
import com.hcmus.smsservice.model.dto.MessageDto;
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

  private AbstractContent generateSendGridContent(MessageDto message) {
    SendGridContent content = new SendGridContent();
    content.setFrom("hcmus.test.1@gmail.com");
    content.setSubject("Car Request");
    content.setTo("hcmus.test.1@gmail.com");
    content.setBody(message.getContent());

    return content;
  }

  private AbstractContent generateTwilioContent(MessageDto message) {
    TwilioContent content = new TwilioContent();
    content.setFrom(twilioConfiguration.getPhoneNumber());
    content.setSubject("Car Request");
//    content.setTo("+18046101470"); // TODO: For test only
    content.setTo("+" + message.getCarRequestDto().getCustomerPhone());
    content.setBody(message.getContent());

    return content;
  }

  public void send(MessageDto message, SenderServiceType serviceType) throws IOException {
    AbstractContent content;

    switch (serviceType) {
      case SENDGRID:
        content = generateSendGridContent(message);
        break;
      case TWILIO:
        content = generateTwilioContent(message);
        break;
      default:
        throw new IllegalArgumentException("Unknown service type: " + serviceType);
    }

    senderFactory.getSenderService(serviceType).send(content);
  }
}