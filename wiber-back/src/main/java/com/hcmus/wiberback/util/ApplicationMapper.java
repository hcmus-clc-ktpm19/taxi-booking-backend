package com.hcmus.wiberback.util;

import com.hcmus.wiberback.model.dto.AccountDto;
import com.hcmus.wiberback.model.dto.CallCenterDto;
import com.hcmus.wiberback.model.dto.CarRequestDto;
import com.hcmus.wiberback.model.dto.CustomerDto;
import com.hcmus.wiberback.model.dto.DriverDto;
import com.hcmus.wiberback.model.entity.mongo.Account;
import com.hcmus.wiberback.model.entity.mongo.CallCenter;
import com.hcmus.wiberback.model.entity.mongo.CarRequest;
import com.hcmus.wiberback.model.entity.mongo.Customer;
import com.hcmus.wiberback.model.entity.mongo.Driver;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ApplicationMapper {

  public AccountDto toAccountDto(Account entity) {
    return AccountDto.builder()
        .id(entity.getId())
        .phone(entity.getPhone())
        .role(entity.getRole())
        .build();
  }

  public CustomerDto toCustomerDto(Customer entity) {
    return CustomerDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .phone(entity.getAccount().getPhone())
        .role(entity.getAccount().getRole())
        .build();
  }

  public DriverDto toDriverDto(Driver entity) {
    return DriverDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .phone(entity.getAccount().getPhone())
            .carType(entity.getCarType())
            .role(entity.getAccount().getRole())
            .build();
  }

  public CallCenterDto toCallCenterDto(CallCenter entity) {
    return CallCenterDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .phone(entity.getAccount().getPhone())
        .role(entity.getAccount().getRole())
        .build();
  }

  public CarRequestDto toCarRequestDto(CarRequest entity) {
    // TODO : get phone with customer has not account
    return CarRequestDto.builder()
        .id(entity.getId())
        .customerId(entity.getCustomer().getId())
        .customerPhone(entity.getCustomer().getAccount().getPhone())
        .pickingAddress(entity.getPickingAddress())
        .arrivingAddress(entity.getArrivingAddress())
        .lngPickingAddress(entity.getLngPickingAddress())
        .latPickingAddress(entity.getLatPickingAddress())
        .lngArrivingAddress(entity.getLngArrivingAddress())
        .latArrivingAddress(entity.getLatArrivingAddress())
        .status(entity.getStatus())
        .build();
  }
}
