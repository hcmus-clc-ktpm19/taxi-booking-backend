package com.hcmus.wiberback.service;

import org.springframework.amqp.core.Queue;

public interface CarRequestMessageSender {
  void send(Object message, Queue queue);
}
