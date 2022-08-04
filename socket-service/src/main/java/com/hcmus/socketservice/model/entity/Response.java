package com.hcmus.socketservice.model.entity;

import com.hcmus.socketservice.model.dto.CarRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message sent to client
 *
 * 封装发给客户端的消息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String response;
    private CarRequestDto carRequestDto;
}
