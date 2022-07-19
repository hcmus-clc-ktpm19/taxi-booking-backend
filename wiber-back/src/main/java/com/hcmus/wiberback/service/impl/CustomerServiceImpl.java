package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.dto.CustomerDto;
import com.hcmus.wiberback.model.entity.Account;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.exception.AccountNotFoundException;
import com.hcmus.wiberback.model.exception.UserNotFoundException;
import com.hcmus.wiberback.repository.AccountRepository;
import com.hcmus.wiberback.repository.CustomerRepository;
import com.hcmus.wiberback.service.CustomerService;
import java.util.List;

import io.netty.util.internal.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Customer findCustomerByPhone(String phone) {
      Account account = accountRepository.findAccountByPhone(phone).orElseThrow(
              () -> new AccountNotFoundException("Account with phone number not found", phone));
  
      return customerRepository.findCustomerByAccount(account).orElseThrow(
              () -> new UserNotFoundException("Customer with phone number not found", phone));
    }
  
  @Override
  @Cacheable(value = "customer", key = "#id", unless = "#result == null")
  public Customer getCustomerById(String id) {
    return customerRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("Customer with id not found", id));
  }

  @Override
  public String saveCustomer(CustomerDto customerRequestDto) {
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
  public String updateCustomer(CustomerDto customerDto) {
    Customer customer = getCustomerById(customerDto.getId());
    customer.setName(customerDto.getName());
    return customerRepository.save(customer).getId();
  }
}
