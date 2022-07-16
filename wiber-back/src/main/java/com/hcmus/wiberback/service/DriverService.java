package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.DriverRequestDto;
import com.hcmus.wiberback.model.entity.Driver;

import java.util.List;

public interface DriverService {
    Driver getDriverById(String id);
    Driver getDriverByPhone(String phone);
    List<Driver> getAllDrivers();
    String saveDriver(DriverRequestDto driverRequestDto);
    Driver updateDriverNameById(String id, DriverRequestDto driverRequestDto);
}
