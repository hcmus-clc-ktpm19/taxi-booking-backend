package com.hcmus.wiberback.entity.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public abstract class AccountDto {

  @NotNull(message = "Phone number is required")
  @NotEmpty(message = "Phone number is required")
  @NotBlank(message = "Phone number is required")
  @Digits(integer = 10, fraction = 0)
  private String phone;
}
