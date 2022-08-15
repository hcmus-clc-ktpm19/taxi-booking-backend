package com.hcmus.smsservice.service;

import com.hcmus.smsservice.model.dto.MessageDto;
import java.io.IOException;
import org.springframework.messaging.handler.annotation.Payload;

public interface CarRequestSub {

  void receive(@Payload MessageDto message) throws IOException;
}