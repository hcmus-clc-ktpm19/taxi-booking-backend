package com.hcmus.smsservice.service.impl;

import com.hcmus.smsservice.model.entity.AbstractContent;
import com.hcmus.smsservice.service.TwilioService;
import org.springframework.stereotype.Service;

@Service
public class TwilioServiceImpl implements TwilioService {

  @Override
  public void send(AbstractContent content) {
    // TODO: SMS service scaffold
  }
}