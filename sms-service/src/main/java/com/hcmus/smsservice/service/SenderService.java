package com.hcmus.smsservice.service;

import com.hcmus.smsservice.model.entity.AbstractContent;
import java.io.IOException;

public interface SenderService {
  void send(AbstractContent content) throws IOException;
}