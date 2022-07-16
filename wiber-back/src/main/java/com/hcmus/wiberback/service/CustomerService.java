package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.CustomerDto;
import com.hcmus.wiberback.model.entity.Customer;
import java.util.List;

public interface CustomerService {

  List<Customer> getAllCustomers();

  Customer getCustomerById(String id);

  String saveCustomer(CustomerDto customerRequestDto);

  String updateCustomer(CustomerDto customerDto);
}
