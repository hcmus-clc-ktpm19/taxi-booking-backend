package com.hcmus.wiberback.entity.dto;

import lombok.Data;

@Data
public class CustomerResponseDto extends CustomerDto {
  private AccountResponseDto account = new AccountResponseDto();
}
