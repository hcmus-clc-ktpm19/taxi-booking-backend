package com.hcmus.socketservice.controller;

import com.hcmus.socketservice.model.dto.CarRequestDto;
import com.hcmus.socketservice.model.entity.ChatMessage;
import com.hcmus.socketservice.model.entity.Message;
import com.hcmus.socketservice.model.entity.PrivateResponse;
import com.hcmus.socketservice.model.entity.Response;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

  // You cannot integrate WebSocket with JWT for token validate.
  private static final String token = "this is a token generated by your code!";
  private final SimpMessagingTemplate simpMessagingTemplate;

  public WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  /**
   * <code>@MessageMapping</code> defines the endpoint for receiving messages, client will send
   * websocket message
   * to endpoints defined in this annotation.
   * <code>@SendTo</code> defines the return value's target endpoint of this method, clients which
   * subscribe to
   * this endpoint will receive the return value of this method. This method will simply send
   * messages received to all clients that subscribe to endpoint specified in
   * <code>@SendTo</code>, just like a broadcast
   *
   * @param //message client message //     * @param authorizationToken customize header, for token
   *                  validate
   * @return return client message to all clients that subscribe to <code>/b</code>
   */
  @RabbitListener(queues = {"${queue.car-request.name}"})
  public void broadcast(CarRequestDto carRequestDto) {
    val response = new Response("Token check failed!", carRequestDto.getLatPickingAddress(),
        carRequestDto.getLngPickingAddress());
    log.info("Received full message: {}", carRequestDto);
    response.setResponse("Get customer from " + carRequestDto.getPickingAddress() + " to "
        + carRequestDto.getArrivingAddress());
    log.info("Broadcast message: {}", response.getResponse());
    simpMessagingTemplate.convertAndSend("/b", response);
  }

//  @RabbitListener(queues = {"${queue.car-request-status.name}"})
//  public void sendToCustomer(CarRequestDto carRequestDto) {
//    PrivateResponse privateResponse = new PrivateResponse();
//    log.info("Receive point-to-point chat message: [" + chatMessage.getFromUserID() + " -> "
//        + chatMessage.getUserID() + ", " + chatMessage.getMessage() + "]");
//    //Response response = new Response("Receive message from user " + chatMessage.getFromUserID() + ": " + chatMessage.getMessage());
//    //simpMessagingTemplate.convertAndSendToUser(String.valueOf(chatMessage.getUserID()), "/msg", response);
//  }
  /**
   * Add a placeholder in <code>@MessageMapping</code> to get the dynamic param in websocket url,
   * for dynamic resending. Message sent to this endpoint will be resent to any clients that
   * subscribe endpoint {@code /g/<groupId>}. Just like a group chat.
   *
   * @param groupID group id
   * @param message client message
   */
  @MessageMapping("/group/{groupID}")
  public void group(@DestinationVariable int groupID, Message message) {
    log.info("Receive group message: [" + groupID + " -> " + message.getName() + "]");
    //Response response = new Response("Welcome to group " + groupID + ", " + message.getName() + "!");
    //simpMessagingTemplate.convertAndSend("/g/" + groupID, response);
  }

  /**
   * Send message to specify user depend on {@link ChatMessage#getUserID}, each user will subscribe
   * himself/herself's endpoint {@code /user/<userId>/msg}, just like point to point chat.
   *
   * @param chatMessage ChatMessage
   */
  @MessageMapping("/chat")
  public void chat(ChatMessage chatMessage) {
    log.info("Receive point-to-point chat message: [" + chatMessage.getFromUserID() + " -> "
        + chatMessage.getUserID() + ", " + chatMessage.getMessage() + "]");
    //Response response = new Response("Receive message from user " + chatMessage.getFromUserID() + ": " + chatMessage.getMessage());
    //simpMessagingTemplate.convertAndSendToUser(String.valueOf(chatMessage.getUserID()), "/msg", response);
  }
}