package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.service.CarRequestMessageSender;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarRequestMessageSenderImpl implements CarRequestMessageSender {
  private final RabbitTemplate rabbitTemplate;

  @Override
  public void send(Object message, Queue queue) {
    rabbitTemplate.convertAndSend(queue.getName(), message);
  }
}
