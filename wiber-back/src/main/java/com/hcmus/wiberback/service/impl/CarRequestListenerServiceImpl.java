package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.dto.CarRequestDto;
import com.hcmus.wiberback.service.CarRequestListenerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CarRequestListenerServiceImpl implements CarRequestListenerService {

  @Override
  @RabbitListener(queues = {"${queue.name}"})
  public void receive(@Payload CarRequestDto message) {
    log.info("Message: {}", message);
  }
}
