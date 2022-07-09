package com.hcmus.wiberback.service;

import com.hcmus.wiberback.entity.dto.AccountRequestDto;
import com.hcmus.wiberback.entity.entity.Account;

public interface AccountService {

  
  Account findAccountByPhone(String phone);

  String saveAccount(AccountRequestDto accountRequestDto);
}
