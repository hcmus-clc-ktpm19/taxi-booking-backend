package com.hcmus.wiberback.service;

import com.hcmus.wiberback.entity.dto.DriverRequestDto;
import com.hcmus.wiberback.entity.entity.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers();
    String saveDriver(DriverRequestDto driverRequestDto);
    String updateDriver(DriverRequestDto driverRequestDto);
}
