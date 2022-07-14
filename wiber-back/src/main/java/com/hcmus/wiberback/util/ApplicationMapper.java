package com.hcmus.wiberback.util;

import com.hcmus.wiberback.entity.dto.CustomerResponseDto;
import com.hcmus.wiberback.entity.dto.DriverResponseDto;
import com.hcmus.wiberback.entity.entity.Customer;
import com.hcmus.wiberback.entity.entity.Driver;
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
  public DriverResponseDto driverToDriverResponseDto(Driver entity) {
    DriverResponseDto dto = new DriverResponseDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPhone(entity.getAccount().getPhone());
    dto.setRole(entity.getAccount().getRole());

    return dto;
  }
}
