package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.dto.CustomerRequestDto;
import com.hcmus.wiberback.entity.dto.CustomerResponseDto;
import com.hcmus.wiberback.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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

  @GetMapping("/all")
  public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
    return ResponseEntity.ok(customerService.getAllCustomers().stream()
        .map(mapper::customerToCustomerResponseDto)
        .collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<String> saveAccount(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
    return ResponseEntity.ok(customerService.saveCustomer(customerRequestDto));
  }
}
