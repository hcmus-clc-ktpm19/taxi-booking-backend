package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.CustomerDto;
import com.hcmus.wiberback.model.dto.DriverRequestDto;
import com.hcmus.wiberback.model.entity.Driver;
import java.util.List;

public interface DriverService {

//  Driver findDriverById(String id);

  Driver findDriverByPhone(String phone);

  List<Driver> findAllDrivers();

  String saveDriver(DriverRequestDto driverRequestDto);

  String updateDriverNameById(String id, DriverRequestDto driverRequestDto);

  String saveOrUpdateDriver(DriverRequestDto driverRequestDto);
}
