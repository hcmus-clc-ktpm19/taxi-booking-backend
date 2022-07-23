package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.dto.DriverRequestDto;
import com.hcmus.wiberback.model.entity.Account;
import com.hcmus.wiberback.model.entity.Driver;
import com.hcmus.wiberback.model.exception.AccountNotFoundException;
import com.hcmus.wiberback.repository.AccountRepository;
import com.hcmus.wiberback.repository.DriverRepository;
import com.hcmus.wiberback.service.DriverService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

  private final DriverRepository driverRepository;
  private final AccountRepository accountRepository;

  @Override
  @Cacheable(value = "driver", key = "#id", unless = "#result == null")
  public Driver findDriverById(String id) {
    return driverRepository
        .findById(id)
        .orElseThrow(() -> new AccountNotFoundException("Driver not found", id));
  }

  @Override
  public Driver findDriverByPhone(String phone) {
//        Account account = accountRepository
//                .findAccountByPhone(phone)
//                .orElseThrow(() -> new AccountNotFoundException("Account not found", phone));
    return driverRepository
        .findAll().stream()
        .filter(driver -> driver.getAccount().getPhone().equals(phone)).findFirst()
        .orElseThrow(() -> new AccountNotFoundException("Driver not found", phone));
  }


  @Override
  public List<Driver> findAllDrivers() {
    return driverRepository.findAll();
  }

  @Override
  public String saveDriver(DriverRequestDto driverRequestDto) {
    Account account = accountRepository
        .findAccountByPhone(driverRequestDto.getPhone())
        .orElseThrow(() -> new AccountNotFoundException("Account not found",
            driverRequestDto.getPhone()));
    Driver driver = new Driver();
    driver.setName(driverRequestDto.getName());
    driver.setAccount(account);
    return driverRepository.save(driver).getId();
  }

  @Override
  public String updateDriverNameById(String id, DriverRequestDto driverRequestDto) {
    Driver updateDriver = driverRepository
        .findById(id)
        .orElseThrow(() -> new AccountNotFoundException("Driver not found", id));
    // driver request dto include phone and name => allow to update name
    if (driverRequestDto.getName() != null) {
      updateDriver.setName(driverRequestDto.getName());
    }
    return driverRepository.save(updateDriver).getId();
  }
}
