package com.hcmus.wiberback.util;

import com.hcmus.wiberback.model.dto.AccountDto;
import com.hcmus.wiberback.model.dto.CallCenterResponseDto;
import com.hcmus.wiberback.model.dto.CustomerDto;
import com.hcmus.wiberback.model.dto.DriverResponseDto;
import com.hcmus.wiberback.model.entity.Account;
import com.hcmus.wiberback.model.entity.CallCenter;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.entity.Driver;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ApplicationMapper {

  public AccountDto toAccountDto(Account entity) {
    return AccountDto.builder()
        .id(entity.getId())
        .phone(entity.getPhone())
        .role(entity.getRole())
        .build();
  }

  public CustomerDto toCustomerDto(Customer entity) {
    return CustomerDto.builder()
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

  public CallCenterResponseDto toCallCenterResponseDto(CallCenter entity) {
    return CallCenterResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .phone(entity.getAccount().getPhone())
        .role(entity.getAccount().getRole())
        .build();
  }

}
