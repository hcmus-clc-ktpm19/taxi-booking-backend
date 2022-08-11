package com.hcmus.socketservice.service.impl;

import com.hcmus.socketservice.model.dto.CarRequestDto;
import com.hcmus.socketservice.model.entity.ChatMessage;
import com.hcmus.socketservice.model.entity.Message;
import com.hcmus.socketservice.model.entity.PrivateResponse;
import com.hcmus.socketservice.model.entity.Response;
import com.hcmus.socketservice.service.WebSocketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketServiceImpl implements WebSocketService {

  private final SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public void broadcastToAvailableDrivers(CarRequestDto carRequestDto) {
    val response = new Response("Token check failed!", carRequestDto);
    log.info("Received full message: {}", carRequestDto);
    response.setResponse("Get customer from " + carRequestDto.getPickingAddress() + " to "
        + carRequestDto.getArrivingAddress());
    log.info("Broadcast message: {}", response.getResponse());
    simpMessagingTemplate.convertAndSend("/b", response);
  }

  @Override
  public void sendAcceptMessageToCustomer(CarRequestDto carRequestDto) {
    Message message = new Message(
        "Driver " + carRequestDto.getDriverName() + " with phone number = "
            + carRequestDto.getDriverPhone() + " has accepted your request!");
    simpMessagingTemplate.convertAndSendToUser(String.valueOf(carRequestDto.getId()), "/msg",
        message);
  }

  @Override
  public void sendPrivateMessageToCustomer(ChatMessage chatMessage) {
    log.info("Receive point-to-point chat message: [" + chatMessage.getLatDriver() + " -> "
        + chatMessage.getToCarRequestId() + ", " + chatMessage.getLngDriver() + "]");
    PrivateResponse privateResponse = new PrivateResponse(chatMessage.getLatDriver(),
        chatMessage.getLngDriver());
    simpMessagingTemplate.convertAndSendToUser(chatMessage.getToCarRequestId(),
        "/msg", privateResponse);
  }
}

