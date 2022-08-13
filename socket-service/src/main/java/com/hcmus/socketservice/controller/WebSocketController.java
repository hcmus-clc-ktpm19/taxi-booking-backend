package com.hcmus.socketservice.controller;

import com.hcmus.socketservice.model.dto.CarRequestDto;
import com.hcmus.socketservice.model.entity.ChatMessage;
import com.hcmus.socketservice.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WebSocketController {
  private final WebSocketService webSocketService;

  /**
   * Receive message from queue then broadcast to all drivers
   * @param carRequestDto Car request data
   */
  @RabbitListener(queues = {"${queue.car-request.name}"})
  public void broadcastToAvailableDrivers(CarRequestDto carRequestDto) {
    webSocketService.broadcastToAvailableDrivers(carRequestDto);
  }

  @RabbitListener(queues = {"${queue.car-request-status.name}"})
  public void sendAcceptMessageToCustomer(CarRequestDto carRequestDto) {
    webSocketService.sendAcceptMessageToCustomer(carRequestDto);
  }

  @RabbitListener(queues = {"${queue.locate-request.name}"})
  public void sendToLocateStaff(CarRequestDto carRequestDto) {
    webSocketService.broadcastToLocateStaff(carRequestDto);
  }

  /**
   * Send message from driver to specify user.
   */
  @MessageMapping("/chat")
  public void chat(ChatMessage chatMessage) {
    webSocketService.sendPrivateMessageToCustomer(chatMessage);
  }
}
