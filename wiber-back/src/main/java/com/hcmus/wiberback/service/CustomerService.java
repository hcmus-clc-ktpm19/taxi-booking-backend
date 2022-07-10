package com.hcmus.wiberback.service;

import com.hcmus.wiberback.entity.dto.CustomerRequestDto;
import com.hcmus.wiberback.entity.entity.Customer;
import java.util.List;

public interface CustomerService {

  List<Customer> getAllCustomers();

  String saveCustomer(CustomerRequestDto customerRequestDto);

  String updateCustomer(CustomerRequestDto customerRequestDto);
}
