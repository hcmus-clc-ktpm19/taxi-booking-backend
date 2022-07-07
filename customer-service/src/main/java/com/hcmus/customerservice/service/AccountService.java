package com.hcmus.customerservice.service;

import com.hcmus.customerservice.model.dto.AccountDto;
import com.hcmus.customerservice.model.entity.Account;

public interface AccountService {

  
  Account findAccountByPhone(String phone);

  String saveAccount(AccountDto accountDto);
}
