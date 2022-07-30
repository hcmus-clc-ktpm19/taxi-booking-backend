package com.hcmus.socketservice.web;

import com.hcmus.socketservice.entity.dto.CarRequestDto;
import com.hcmus.socketservice.entity.dto.ChatMessage;
import com.hcmus.socketservice.entity.dto.Message;
import com.hcmus.socketservice.entity.dto.Response;
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
     * <code>@MessageMapping</code> defines the endpoint for receiving messages, client will send websocket message
     * to endpoints defined in this annotation.
     * <code>@SendTo</code> defines the return value's target endpoint of this method, clients which subscribe to
     * this endpoint will receive the return value of this method.
     * This method will simply send messages received to all clients that subscribe to endpoint specified in
     * <code>@SendTo</code>, just like a broadcast
     *
     * <code>@MessageMapping</code> 定义接收消息的端点，客户端发送 WebSocket 消息到此端点。
     * <code>@SendTo</code> 定义方法返回值发送的端点，订阅该端点的客户端可以收到服务器端的回复。
     * 此端点默认将收到的消息发送到所有订阅了 <code>@SendTo</code> 端点的客户端，相当于广播。
     *
     * @param //message            client message
     * @param authorizationToken customize header, for token validate
     * @return return client message to all clients that subscribe to <code>/b</code>
     */
    //
    @MessageMapping("/broadcast")
    @SendTo("/b")
    public Response broadcast(CarRequestDto carRequestDtoInfo, @Header(value = "authorization") String authorizationToken) {
        val response = new Response("Token check failed!", carRequestDtoInfo.getLatPickingAddress(), carRequestDtoInfo.getLngPickingAddress());
        if (authorizationToken.equals(token)) {
            log.info("Token check success!!!");
            log.info("Received message: {}", carRequestDtoInfo.getCustomerId());
            response.setResponse("Get customer from " + carRequestDtoInfo.getPickingAddress() + " to "  + carRequestDtoInfo.getArrivingAddress());
        } else {
            log.info(response.getResponse());
        }
        return response;
    }

    /**
     * Add a placeholder in <code>@MessageMapping</code> to get the dynamic param in websocket url, for dynamic
     * resending. Message sent to this endpoint will be resent to any clients that subscribe endpoint {@code /g/<groupId>}.
     * Just like a group chat.
     * <p>
     * 通过在 <code>@MessageMapping</code> 中添加消息占位符来获取 url 内容，从而动态转发。
     * 消息会发送到所有订阅了 {@code /g/<groupId>} 的客户端，实现效果相当于群聊
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
     * Send message to specify user depend on {@link ChatMessage#getUserID}, each user will subscribe himself/herself's endpoint
     * {@code /user/<userId>/msg}, just like point to point chat.
     * <p>
     * 依据 {@code ChatMessage} 中的内容发送消息给特定用户，每个用户都订阅自己接受消息的端点
     * {@code /user/<userId>/msg}，实现效果类似点对点聊天
     *
     * @param chatMessage 聊天消息
     */
    @MessageMapping("/chat")
    public void chat(ChatMessage chatMessage) {
        log.info("Receive point-to-point chat message: [" + chatMessage.getFromUserID() + " -> " + chatMessage.getUserID() + ", " + chatMessage.getMessage() + "]");
        //Response response = new Response("Receive message from user " + chatMessage.getFromUserID() + ": " + chatMessage.getMessage());
        //simpMessagingTemplate.convertAndSendToUser(String.valueOf(chatMessage.getUserID()), "/msg", response);
    }
}
