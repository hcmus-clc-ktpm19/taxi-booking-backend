package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.model.dto.CarRequestDto;
import com.hcmus.wiberback.service.CarRequestMessageSender;
import com.hcmus.wiberback.service.CarRequestService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/car-request")
public class CarRequestController extends AbstractApplicationController {

  private final CarRequestMessageSender queueProducer;
  private final CarRequestService carRequestService;
  @Qualifier("smsQueue")
  private final Queue smsQueue;

  @PreAuthorize("hasAnyRole('ADMIN', 'CALLCENTER')")
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
  public ResponseEntity<List<CarRequestDto>> findCarRequestsByCustomerId(
      @PathVariable String customerId) {
    return ResponseEntity.ok(
        carRequestService.findCarRequestsByCustomerId(customerId).stream()
            .map(mapper::toCarRequestDto)
            .collect(Collectors.toList()));
  }

  @PostMapping("/create-or-update")
  public ResponseEntity<String> saveOrUpdateCarRequest(@RequestBody CarRequestDto carRequestDto) {
    String id = carRequestService.saveOrUpdateCarRequest(carRequestDto);
    return ResponseEntity.ok(id);
  }

  @PostMapping("/callcenter/create-or-update")
  public ResponseEntity<String> saveCarRequestCallCenter(@RequestBody CarRequestDto carRequestDto) {
    String id = carRequestService.saveCarRequestCallCenter(carRequestDto);
    return ResponseEntity.ok(id);
  }
}
