package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.model.dto.CarRequestDto;
import com.hcmus.wiberback.service.CarRequestService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarRequestController extends AbstractApplicationController {

  private final CarRequestService carRequestService;

  @Autowired
  CarRequestController(CarRequestService carRequestService) {
    this.carRequestService = carRequestService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<CarRequestDto>> findAllCarRequests() {
    return ResponseEntity.ok(carRequestService.findAllCarRequests().stream()
        .map(mapper::toCarRequestDto)
        .collect(Collectors.toList()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarRequestDto> findCarRequestById(@PathVariable String id) {
    return ResponseEntity.ok(mapper.toCarRequestDto(carRequestService.findCarRequestById(id)));
  }

  @GetMapping("/customer-car-requests/{customerId}")
  public ResponseEntity<List<CarRequestDto>> findCarRequestsByCustomerId(@PathVariable String customerId) {
    return ResponseEntity.ok(
        carRequestService.findCarRequestsByCustomerId(customerId).stream()
            .map(mapper::toCarRequestDto)
            .collect(Collectors.toList()));
  }

  @PostMapping("/create-or-update")
  public ResponseEntity<String> saveOrUpdateCarRequest(@RequestBody CarRequestDto carRequestDto) {
    return ResponseEntity.ok(carRequestService.saveOrUpdateCarRequest(carRequestDto));
  }
}
