package com.hcmus.wiberback.util;

import com.hcmus.wiberback.entity.dto.AccountResponseDto;
import com.hcmus.wiberback.entity.dto.CustomerResponseDto;
import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.entity.Customer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ApplicationMapper {

  public AccountResponseDto accountToAccountResponseDto(Account entity) {
    AccountResponseDto dto = new AccountResponseDto();
    dto.setId(entity.getId());
    dto.setPhone(entity.getPhone());

    return dto;
  }

  public CustomerResponseDto customerToCustomerResponseDto(Customer entity) {
    CustomerResponseDto dto = new CustomerResponseDto();
    dto.setName(entity.getName());
    dto.getAccount().setId(entity.getId());
    dto.getAccount().setPhone(entity.getPhone());

    return dto;
  }
}
