package com.hcmus.smsservice.service.impl;

import com.hcmus.smsservice.model.entity.AbstractContent;
import com.hcmus.smsservice.service.SendGridService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendGridServiceImpl implements SendGridService {

  @Autowired
  private SendGrid sendGrid;
  @Autowired
  private Request request;

  @Override
  public void send(AbstractContent content) {
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(content.build());
      Response response = sendGrid.api(request);

      log.info("Status code: {}", response.getStatusCode());
      log.info("Headers: {}", response.getHeaders());
      log.info("Body: {}", response.getBody());
    } catch (IOException ex) {
      log.error("Error: {}", ex.getMessage());
    }
  }
}