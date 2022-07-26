package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.dto.CarRequestDto;
import com.hcmus.wiberback.model.entity.CarRequest;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.exception.UserNotFoundException;
import com.hcmus.wiberback.repository.CarRequestRepository;
import com.hcmus.wiberback.repository.CustomerRepository;
import com.hcmus.wiberback.service.CarRequestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRequestServiceImpl implements CarRequestService {

  private final CarRequestRepository carRequestRepository;
  private final CustomerRepository customerRepository;

  @Override
  public List<CarRequest> findAllCarRequests() {
    return carRequestRepository.findAll();
  }

  @Override
  public CarRequest findCarRequestById(String id) {
    // TODO: Implement specific exception for car request not found later
    return carRequestRepository.findById(id).orElseThrow(() -> new IllegalStateException());
  }

  @Override
  public List<CarRequest> findCarRequestsByCustomerId(String customerId) {
    // TODO: Implement specific exception for car request not found later
    return carRequestRepository.findCarRequestsByCustomer(
        customerRepository
            .findById(customerId)
            .orElseThrow(() -> new UserNotFoundException("Customer not found", customerId)));
  }

  @Override
  public String saveOrUpdateCarRequest(CarRequestDto carRequestDto) {
    Customer customer = customerRepository
        .findById(carRequestDto.getCustomerId())
        .orElseThrow(
            () -> new UserNotFoundException("Customer not found", carRequestDto.getCustomerId()));

    CarRequest carRequest;
    if (carRequestDto.getId() == null) {
      carRequest = CarRequest.builder().customer(customer)
          .pickingAddress(carRequestDto.getPickingAddress())
          .arrivingAddress(carRequestDto.getArrivingAddress())
          .lngPickingAddress(carRequestDto.getLngPickingAddress())
          .latPickingAddress(carRequestDto.getLatPickingAddress())
          .lngArrivingAddress(carRequestDto.getLngArrivingAddress())
          .latArrivingAddress(carRequestDto.getLatArrivingAddress()).build();
    } else {
      // TODO: Implement specific exception for car request not found later
      carRequest = carRequestRepository.findById(carRequestDto.getId())
          .orElseThrow(() -> new IllegalStateException());
      carRequest.setPickingAddress(carRequestDto.getPickingAddress());
      carRequest.setArrivingAddress(carRequestDto.getArrivingAddress());
      carRequest.setLngPickingAddress(carRequestDto.getLngPickingAddress());
      carRequest.setLatPickingAddress(carRequestDto.getLatPickingAddress());
      carRequest.setLngArrivingAddress(carRequestDto.getLngArrivingAddress());
      carRequest.setLatArrivingAddress(carRequestDto.getLatArrivingAddress());
    }

    return carRequestRepository.save(carRequest).getId();
  }
}
