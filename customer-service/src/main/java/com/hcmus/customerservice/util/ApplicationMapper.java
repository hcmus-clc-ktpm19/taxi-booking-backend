package com.hcmus.customerservice.util;

import com.hcmus.customerservice.model.dto.AccountDto;
import com.hcmus.customerservice.model.entity.Account;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ApplicationMapper {

  public AccountDto accountToAccountDto(Account account) {
    AccountDto dto = new AccountDto();
    dto.setId(account.getId());
    dto.setPhone(account.getPhone());
    dto.setPassword(account.getPassword());

    return dto;
  }
}
