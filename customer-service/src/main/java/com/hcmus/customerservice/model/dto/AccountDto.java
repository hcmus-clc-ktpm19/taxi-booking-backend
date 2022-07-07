package com.hcmus.customerservice.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

  private String id;

  @NotNull(message = "Phone number is required")
  @NotEmpty(message = "Phone number is required")
  @NotBlank(message = "Phone number is required")
  @Digits(integer = 10, fraction = 0)
  private String phone;

  @NotNull(message = "Password is required")
  @NotEmpty(message = "Password is required")
  @NotBlank(message = "Password is required")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
      message = "Password must be at least 8 characters and contain at least one letter and one number")
  private String password;
}
