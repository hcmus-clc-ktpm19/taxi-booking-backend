package com.hcmus.wiberback.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CallCenterRequestDto {
  @NotEmpty(message = "Phone number is required")
  @NotBlank(message = "Phone number is required")
  @Digits(integer = 10, fraction = 0)
  private String phone;

  @NotEmpty(message = "Name number is required")
  @NotBlank(message = "Name number is required")
  private String name;
}
