package com.hcmus.smsservice.service;

import com.hcmus.smsservice.model.dto.CarRequestDto;
import java.io.IOException;
import org.springframework.messaging.handler.annotation.Payload;

public interface CarRequestListenerService {

  void receive(@Payload CarRequestDto message) throws IOException;
}