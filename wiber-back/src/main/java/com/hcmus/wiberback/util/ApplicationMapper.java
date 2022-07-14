package com.hcmus.wiberback.util;

import com.hcmus.wiberback.entity.dto.AccountDto;
import com.hcmus.wiberback.entity.dto.CustomerResponseDto;
import com.hcmus.wiberback.entity.dto.DriverResponseDto;
import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.entity.Customer;
import com.hcmus.wiberback.entity.entity.Driver;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ApplicationMapper {

  public AccountDto toAccountDto(Account entity) {
    return AccountDto.builder()
        .phone(entity.getPhone())
        .role(entity.getRole())
        .build();
  }

  public CustomerResponseDto toCustomerResponseDto(Customer entity) {
    return CustomerResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .phone(entity.getAccount().getPhone())
        .role(entity.getAccount().getRole())
        .build();
  }

  public DriverResponseDto toDriverResponseDto(Driver entity) {
    return DriverResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .phone(entity.getAccount().getPhone())
        .role(entity.getAccount().getRole())
        .build();
  }
}
