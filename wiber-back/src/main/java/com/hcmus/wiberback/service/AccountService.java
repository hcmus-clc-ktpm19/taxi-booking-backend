package com.hcmus.wiberback.service;

import com.hcmus.wiberback.entity.dto.AccountRequestDto;
import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.enums.Role;

public interface AccountService {

  
  Account findAccountByPhone(String phone);

  String saveAccount(AccountRequestDto accountRequestDto, Role role);
}
