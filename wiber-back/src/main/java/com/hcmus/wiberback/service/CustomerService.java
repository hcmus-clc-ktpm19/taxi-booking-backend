package com.hcmus.wiberback.service;

import com.hcmus.wiberback.entity.dto.CustomerRequestDto;
import com.hcmus.wiberback.entity.entity.Customer;

public interface CustomerService {


  Customer findAccountByPhone(String phone);

  String saveAccount(CustomerRequestDto customerRequestDto);
}
