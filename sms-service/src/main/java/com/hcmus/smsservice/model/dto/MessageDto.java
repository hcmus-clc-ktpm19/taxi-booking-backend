package com.hcmus.smsservice.model.dto;

import com.hcmus.smsservice.model.enums.SenderServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
  private CarRequestDto carRequestDto;
  private String content;
  private SenderServiceType senderServiceType;
}
