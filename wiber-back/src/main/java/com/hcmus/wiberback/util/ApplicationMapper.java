package com.hcmus.wiberback.util;

import com.hcmus.wiberback.entity.dto.AccountDto;
import com.hcmus.wiberback.entity.entity.Account;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ApplicationMapper {

  public AccountDto accountToAccountDto(Account account) {
    AccountDto dto = new AccountDto();
    dto.setId(account.getId());
    dto.setPhone(account.getPhone());

    return dto;
  }
}
