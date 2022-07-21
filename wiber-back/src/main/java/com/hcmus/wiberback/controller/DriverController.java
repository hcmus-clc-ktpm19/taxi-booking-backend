package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.model.dto.DriverRequestDto;
import com.hcmus.wiberback.model.dto.DriverResponseDto;
import com.hcmus.wiberback.service.DriverService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driver")
public class DriverController extends AbstractApplicationController {

  private final DriverService driverService;

  @GetMapping("/{id}")
  public ResponseEntity<DriverResponseDto> findDriverById(@PathVariable String id) {
    return ResponseEntity.ok(mapper.toDriverResponseDto(driverService.findDriverById(id)));
  }

  @GetMapping
  public ResponseEntity<DriverResponseDto> findDriverByPhone(@RequestParam String q) {
    return ResponseEntity.ok(mapper.toDriverResponseDto(driverService.findDriverByPhone(q)));
  }

  @GetMapping("/all")
  public ResponseEntity<List<DriverResponseDto>> findAllDrivers() {
    return ResponseEntity.ok(driverService.findAllDrivers().stream()
        .map(mapper::toDriverResponseDto)
        .collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<String> saveAccount(@Valid @RequestBody DriverRequestDto driverRequestDto) {
    return ResponseEntity.ok(driverService.saveDriver(driverRequestDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateDriverNameById(@PathVariable String id,
      @Valid @RequestBody DriverRequestDto driverRequestDto) {
    return ResponseEntity.ok(driverService.updateDriverNameById(id, driverRequestDto));
  }
}
