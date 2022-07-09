package com.hcmus.wiberback.entity.dto;

import lombok.Data;

@Data
public class CustomerRequestDto extends CustomerDto {
  private AccountRequestDto account;
}
