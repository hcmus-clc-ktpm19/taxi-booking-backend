package com.hcmus.wiberback.entity.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomerResponseDto {
  @NotEmpty
  @NotBlank
  private String id;

  @NotEmpty
  @NotBlank
  private String name;

  @NotEmpty(message = "Phone number is required")
  @NotBlank(message = "Phone number is required")
  @Digits(integer = 10, fraction = 0)
  private String phone;
}
