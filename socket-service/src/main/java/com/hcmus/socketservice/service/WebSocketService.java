package com.hcmus.socketservice.service;


import com.hcmus.socketservice.model.dto.CarRequestDto;
import com.hcmus.socketservice.model.entity.ChatMessage;

public interface WebSocketService {

  void broadcastToAvailableDrivers(CarRequestDto carRequestDto);

  void sendAcceptMessageToCustomer(CarRequestDto carRequestDto);

  void sendPrivateMessageToCustomer(ChatMessage chatMessage);

  void broadcastToLocateStaff(CarRequestDto carRequestDto);
}
