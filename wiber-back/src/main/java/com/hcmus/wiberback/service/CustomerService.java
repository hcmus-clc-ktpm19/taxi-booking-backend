package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.CustomerDto;
import com.hcmus.wiberback.model.entity.Customer;
import java.util.List;

public interface CustomerService {

  List<Customer> getAllCustomers();

  Customer findCustomerByPhone(String phone);

  Customer findCustomerById(String id);

  String saveOrUpdateCustomer(CustomerDto customerRequestDto);
}
