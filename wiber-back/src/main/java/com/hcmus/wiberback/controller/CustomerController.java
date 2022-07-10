package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.dto.CustomerAuthRequestDto;
import com.hcmus.wiberback.entity.dto.CustomerResponseDto;
import com.hcmus.wiberback.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController extends AbstractApplicationController {
  private final CustomerService customerService;

  @GetMapping("/customer-details")
  public ResponseEntity<CustomerResponseDto> findAccountByPhone(String phone) {
    return ResponseEntity.ok(mapper.customerToCustomerResponseDto(customerService.findAccountByPhone(phone)));
  }

  @PostMapping("/auth/register")
  public ResponseEntity<String> saveAccount(@RequestBody CustomerAuthRequestDto customerAuthRequestDto) {
    return ResponseEntity.ok(customerService.saveAccount(customerAuthRequestDto));
  }
}
