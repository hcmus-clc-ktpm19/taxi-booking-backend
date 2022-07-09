package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.entity.dto.CustomerRequestDto;
import com.hcmus.wiberback.entity.entity.Customer;
import com.hcmus.wiberback.entity.exception.AccountNotFoundException;
import com.hcmus.wiberback.repository.CustomerRepository;
import com.hcmus.wiberback.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CustomerRepository customerRepository;

  @Override
  public Customer findAccountByPhone(String phone) {
    return customerRepository.findCustomerByPhone(phone)
        .orElseThrow(() -> new AccountNotFoundException("Account not found", phone));
  }

  @Override
  public String saveAccount(CustomerRequestDto customerRequestDto) {
    Customer customer = new Customer();
    customer.setPhone(customerRequestDto.getAccount().getPhone());
    customer.setPassword(bCryptPasswordEncoder.encode(customerRequestDto.getAccount().getPassword()));
    customer.setName(customerRequestDto.getName());

    return customerRepository.save(customer).getId();
  }
}
