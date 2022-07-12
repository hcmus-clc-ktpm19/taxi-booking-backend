package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.entity.dto.CustomerRequestDto;
import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.entity.Customer;
import com.hcmus.wiberback.entity.exception.AccountNotFoundException;
import com.hcmus.wiberback.repository.AccountRepository;
import com.hcmus.wiberback.repository.CustomerRepository;
import com.hcmus.wiberback.service.CustomerService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final AccountRepository accountRepository;

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public String saveCustomer(CustomerRequestDto customerRequestDto) {
    Account account =
        accountRepository
            .findAccountByPhone(customerRequestDto.getPhone())
            .orElseThrow(() -> new AccountNotFoundException("Account not found",
                customerRequestDto.getPhone()));

    Customer customer = new Customer();
    customer.setName(customerRequestDto.getName());
    customer.setAccount(account);

    return customerRepository.save(customer).getId();
  }

  @Override
  public String updateCustomer(CustomerRequestDto customerRequestDto) {
    return null;
  }
}
