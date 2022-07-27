package com.hcmus.smsservice.service.impl;

import com.hcmus.smsservice.model.dto.CarRequestDto;
import com.hcmus.smsservice.model.enums.SenderServiceType;
import com.hcmus.smsservice.service.CarRequestListenerService;
import com.hcmus.smsservice.util.SenderFacade;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarRequestListenerServiceImpl implements CarRequestListenerService {

  private final SenderFacade senderFacade;

  @Override
  @RabbitListener(queues = {"${queue.name}"})
  public void receive(@Payload CarRequestDto carRequestDto) throws IOException {
    senderFacade.send(carRequestDto, SenderServiceType.TWILIO);
  }
}
