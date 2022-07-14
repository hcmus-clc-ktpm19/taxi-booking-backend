package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.entity.dto.DriverRequestDto;
import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.entity.Driver;
import com.hcmus.wiberback.entity.exception.AccountNotFoundException;
import com.hcmus.wiberback.repository.AccountRepository;
import com.hcmus.wiberback.repository.DriverRepository;
import com.hcmus.wiberback.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final AccountRepository accountRepository;

    @Override
    public Driver getDriverById(String id) {
        return driverRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Driver not found", id));
    }

    @Override
    public Driver getDriverByPhone(String phone) {
//        Account account = accountRepository
//                .findAccountByPhone(phone)
//                .orElseThrow(() -> new AccountNotFoundException("Account not found", phone));
        return driverRepository
                .findAll().stream()
                .filter(driver -> driver.getAccount().getPhone().equals(phone)).findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account not found", phone));
    }


    @Override
    public List<Driver> getAllDrivers() {
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
    public String updateDriver(DriverRequestDto driverRequestDto) {
        return null;
    }

    @Override
    public String deleteDriver(String id) {
        return null;
    }
}
