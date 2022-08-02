package com.hcmus.wiberback.service;

import com.hcmus.wiberback.model.dto.CarRequestDto;
import com.hcmus.wiberback.model.entity.CarRequest;
import java.util.List;

public interface CarRequestService {
  List<CarRequest> findAllCarRequests();
  CarRequest findCarRequestById(String id);
  List<CarRequest> findCarRequestsByCustomerId(String customerId);
  String saveOrUpdateCarRequest(CarRequestDto carRequestDto);

  String acceptCarRequest(CarRequestDto carRequestDto);
}
