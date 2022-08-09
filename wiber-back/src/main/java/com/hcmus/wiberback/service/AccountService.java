package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.AccountDto;
import com.hcmus.wiberback.model.entity.Account;

public interface AccountService {

  Account findAccountByPhone(String phone);

  String saveAccount(AccountDto accountDto);

  String updatePasswordById(String id, AccountDto accountDto);
}
