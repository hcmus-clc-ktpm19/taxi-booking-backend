package com.hcmus.socketservice.model.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String toCarRequestId;
    private Double latDriver;
    private Double lngDriver;
}
