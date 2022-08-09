package com.hcmus.socketservice.model.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String fromUserId;
    private String toCarRequestId;
    private String message;
}
