package com.hcmus.socketservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message sent to server by browser
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String message;
}
