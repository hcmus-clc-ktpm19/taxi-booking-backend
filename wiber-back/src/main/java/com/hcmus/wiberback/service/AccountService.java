package com.hcmus.wiberback.service;

import com.hcmus.wiberback.entity.dto.AccountDto;
import com.hcmus.wiberback.entity.entity.Account;

public interface AccountService {


  Account findAccountByPhone(String phone);

  String saveAccount(AccountDto accountDto);
  String updatePasswordById(String id, AccountDto accountDto);
}
