package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.CustomerDto;
import com.hcmus.wiberback.model.entity.Customer;
import java.util.List;

public interface CustomerService {

  List<Customer> getAllCustomers();

  Customer findCustomerByPhone(String phone);

  Customer getCustomerById(String id);

  Customer getCustomerByPhone(String phone);

  String saveOrUpdateCustomer(CustomerDto customerRequestDto);
}
