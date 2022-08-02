package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.dto.CarRequestDto;
import com.hcmus.wiberback.model.entity.CallCenter;
import com.hcmus.wiberback.model.entity.CarRequest;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.exception.CarRequestNotFoundException;
import com.hcmus.wiberback.model.exception.UserNotFoundException;
import com.hcmus.wiberback.repository.CallCenterRepository;
import com.hcmus.wiberback.repository.CarRequestRepository;
import com.hcmus.wiberback.repository.CustomerRepository;
import com.hcmus.wiberback.service.CarRequestService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRequestServiceImpl implements CarRequestService {

  private final CarRequestRepository carRequestRepository;
  private final CustomerRepository customerRepository;
  private final CallCenterRepository callCenterRepository;

  @Override
  public List<CarRequest> findAllCarRequests() {
    return carRequestRepository.findAll();
  }

  @Override
  public CarRequest findCarRequestById(String id) {
    return carRequestRepository.findById(id)
        .orElseThrow(() -> new CarRequestNotFoundException("Car request not found", id));
  }

  @Override
  public List<CarRequest> findCarRequestsByCustomerId(String customerId) {
    return carRequestRepository.findCarRequestsByCustomer(
        customerRepository
            .findById(customerId)
            .orElseThrow(() -> new UserNotFoundException("Customer not found", customerId)));
  }

  @Override
  public String saveOrUpdateCarRequest(CarRequestDto carRequestDto) {
    Customer customer = new Customer();
    CallCenter callCenter = new CallCenter();
    if(carRequestDto.getCustomerId() != null){
      customer = customerRepository
          .findById(carRequestDto.getCustomerId())
          .orElseThrow(
              () -> new UserNotFoundException("Customer not found", carRequestDto.getCustomerId()));
    } else {
      callCenter = callCenterRepository
          .findById(carRequestDto.getCallCenterId())
          .orElseThrow(
              () -> new UserNotFoundException("Callcenter not found", carRequestDto.getCallCenterId()));
    }

    CarRequest carRequest;
    if (carRequestDto.getId() == null) {
      if(carRequestDto.getCustomerId() != null){
        carRequest = CarRequest.builder().customer(customer)
            .pickingAddress(carRequestDto.getPickingAddress())
            .arrivingAddress(carRequestDto.getArrivingAddress())
            .lngPickingAddress(carRequestDto.getLngPickingAddress())
            .latPickingAddress(carRequestDto.getLatPickingAddress())
            .lngArrivingAddress(carRequestDto.getLngArrivingAddress())
            .latArrivingAddress(carRequestDto.getLatArrivingAddress()).build();
      } else {
        carRequest = CarRequest.builder().callCenter(callCenter)
            .pickingAddress(carRequestDto.getPickingAddress())
//            .arrivingAddress(carRequestDto.getArrivingAddress())
//            .lngPickingAddress(carRequestDto.getLngPickingAddress())
//            .latPickingAddress(carRequestDto.getLatPickingAddress())
//            .lngArrivingAddress(carRequestDto.getLngArrivingAddress())
//            .latArrivingAddress(carRequestDto.getLatArrivingAddress())
            .carType(carRequestDto.getCarType())
            .createdAt(new Date())
            .updatedAt(new Date())
            .build();

      }
    } else {
      carRequest = carRequestRepository.findById(carRequestDto.getId())
          .orElseThrow(() -> new CarRequestNotFoundException("Car request not found",
              carRequestDto.getId()));
      carRequest.setPickingAddress(carRequestDto.getPickingAddress());
      carRequest.setArrivingAddress(carRequestDto.getArrivingAddress());
      carRequest.setLngPickingAddress(carRequestDto.getLngPickingAddress());
      carRequest.setLatPickingAddress(carRequestDto.getLatPickingAddress());
      carRequest.setLngArrivingAddress(carRequestDto.getLngArrivingAddress());
      carRequest.setLatArrivingAddress(carRequestDto.getLatArrivingAddress());
      carRequest.setUpdatedAt(new Date());
    }

    return carRequestRepository.save(carRequest).getId();
  }
}
