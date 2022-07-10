package com.hcmus.wiberback.service;

import com.hcmus.wiberback.entity.dto.CustomerAuthRequestDto;
import com.hcmus.wiberback.entity.entity.Customer;

public interface CustomerService {


  Customer findAccountByPhone(String phone);

  String saveAccount(CustomerAuthRequestDto customerAuthRequestDto);
}
