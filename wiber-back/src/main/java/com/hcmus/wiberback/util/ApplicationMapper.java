package com.hcmus.wiberback.util;

import com.hcmus.wiberback.entity.dto.CustomerResponseDto;
import com.hcmus.wiberback.entity.entity.Customer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ApplicationMapper {
  public CustomerResponseDto customerToCustomerResponseDto(Customer entity) {
    CustomerResponseDto dto = new CustomerResponseDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPhone(entity.getAccount().getPhone());
    dto.setRole(entity.getAccount().getRole());

    return dto;
  }
}
