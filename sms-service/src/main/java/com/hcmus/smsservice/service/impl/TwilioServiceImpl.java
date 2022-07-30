package com.hcmus.smsservice.service.impl;

import com.hcmus.smsservice.config.TwilioConfiguration;
import com.hcmus.smsservice.model.entity.AbstractContent;
import com.hcmus.smsservice.service.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TwilioServiceImpl implements TwilioService {

  @Autowired
  private TwilioConfiguration twilioConfiguration;

  @Override
  public void send(AbstractContent content) throws IOException {
    Twilio.init(twilioConfiguration.getSid(), twilioConfiguration.getToken());

    Message message = Message.creator(
            new PhoneNumber(content.getTo()),
            new PhoneNumber(content.getFrom()),
            content.build())
        .create();

    log.info("Message sent: {}", message.getBody());
    log.info("Message status: {}", message.getStatus());
  }
}