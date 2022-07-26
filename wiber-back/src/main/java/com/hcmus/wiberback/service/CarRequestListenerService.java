package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.CarRequestDto;
import org.springframework.messaging.handler.annotation.Payload;

public interface CarRequestListenerService {

  void receive(@Payload CarRequestDto message);
}
