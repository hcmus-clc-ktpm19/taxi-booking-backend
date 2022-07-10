package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.entity.dto.CustomerAuthRequestDto;
import com.hcmus.wiberback.entity.entity.Customer;
import com.hcmus.wiberback.entity.exception.AccountNotFoundException;
import com.hcmus.wiberback.entity.exception.ExistedAccountException;
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
  public String saveAccount(CustomerAuthRequestDto customerAuthRequestDto) {
    if (customerRepository.existsByPhone(customerAuthRequestDto.getPhone())) {
      throw new ExistedAccountException("Account with phone already exists", customerAuthRequestDto.getPhone());
    }

    Customer customer = new Customer();
    customer.setPhone(customerAuthRequestDto.getPhone());
    customer.setPassword(bCryptPasswordEncoder.encode(customerAuthRequestDto.getPassword()));

    return customerRepository.save(customer).getId();
  }
}
