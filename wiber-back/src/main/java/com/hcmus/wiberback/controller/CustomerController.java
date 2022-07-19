package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.model.dto.CustomerDto;
import com.hcmus.wiberback.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController extends AbstractApplicationController {

  private final CustomerService customerService;

  @GetMapping("/all")
  public ResponseEntity<List<CustomerDto>> getAllCustomers() {
    return ResponseEntity.ok(customerService.getAllCustomers().stream()
        .map(mapper::toCustomerDto)
        .collect(Collectors.toList()));
  }

  @GetMapping
  public ResponseEntity<CustomerDto> getCustomerById(@RequestParam String id) {
    return ResponseEntity.ok(mapper.toCustomerDto(customerService.getCustomerById(id)));
  }
  
  @GetMapping("/{phone}")
  public ResponseEntity<CustomerDto> findCustomerByPhone(@PathVariable String phone) {
    return ResponseEntity.ok(mapper.toCustomerDto(customerService.findCustomerByPhone(phone)));
  }
  
  @PostMapping
  public ResponseEntity<String> saveAccount(
      @Valid @RequestBody CustomerDto customerRequestDto) {
    return ResponseEntity.ok(customerService.saveCustomer(customerRequestDto));
  }

  @PutMapping
  public ResponseEntity<String> updateAccount(
      @Valid @RequestBody CustomerDto customerDto) {
    return ResponseEntity.ok(customerService.updateCustomer(customerDto));
  }
}
