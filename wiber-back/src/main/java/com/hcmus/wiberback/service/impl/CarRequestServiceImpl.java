package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.dto.CarRequestDto;
import com.hcmus.wiberback.model.entity.CarRequest;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.enums.CarRequestStatus;
import com.hcmus.wiberback.model.exception.CarRequestNotFoundException;
import com.hcmus.wiberback.model.exception.UserNotFoundException;
import com.hcmus.wiberback.repository.CarRequestRepository;
import com.hcmus.wiberback.repository.CustomerRepository;
import com.hcmus.wiberback.service.CarRequestMessageSender;
import com.hcmus.wiberback.service.CarRequestService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRequestServiceImpl implements CarRequestService {

  private final CarRequestRepository carRequestRepository;
  private final CustomerRepository customerRepository;
  private final CarRequestMessageSender queueProducer;
  @Qualifier("carRequestQueue")
  private final Queue carRequestQueue;
  @Qualifier("carRequestStatusQueue")
  private final Queue carRequestStatusQueue;

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
          .latArrivingAddress(carRequestDto.getLatArrivingAddress())
          .status(carRequestDto.getStatus()).build();
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
      carRequest.setStatus(carRequestDto.getStatus());
    }
    String carRequestId = carRequestRepository.save(carRequest).getId();
    carRequestDto.setId(carRequestId);
    // create a new car request to car-request-queue
    if(Objects.equals(carRequestDto.getStatus(), CarRequestStatus.WAITING.name())) {
      queueProducer.send(carRequestDto, carRequestQueue);
    }else if(Objects.equals(carRequestDto.getStatus(), CarRequestStatus.ACCEPTED.name())) {
      queueProducer.send(carRequestDto, carRequestStatusQueue);
    }
    return carRequestId;
  }
}
