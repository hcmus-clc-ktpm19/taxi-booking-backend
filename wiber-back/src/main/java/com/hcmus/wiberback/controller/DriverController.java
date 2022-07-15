package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.dto.DriverRequestDto;
import com.hcmus.wiberback.entity.dto.DriverResponseDto;
import com.hcmus.wiberback.service.DriverService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driver")
public class DriverController extends AbstractApplicationController {

  private final DriverService driverService;

  @GetMapping("/{id}")
  public ResponseEntity<DriverResponseDto> getDriverById(@PathVariable String id) {
    return ResponseEntity.ok(mapper.toDriverResponseDto(driverService.getDriverById(id)));
  }

  @GetMapping
  public ResponseEntity<DriverResponseDto> getDriverByPhone(@RequestParam String q) {
    return ResponseEntity.ok(mapper.toDriverResponseDto(driverService.getDriverByPhone(q)));
  }

  @GetMapping("/all")
  public ResponseEntity<List<DriverResponseDto>> getAllDrivers() {
    return ResponseEntity.ok(driverService.getAllDrivers().stream()
        .map(mapper::toDriverResponseDto)
        .collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<String> saveAccount(@Valid @RequestBody DriverRequestDto driverRequestDto) {
    return ResponseEntity.ok(driverService.saveDriver(driverRequestDto));
  }
  @PutMapping("/{id}")
  public ResponseEntity<DriverResponseDto> updateDriverNameById(@PathVariable String id,
                                                            @Valid @RequestBody DriverRequestDto driverRequestDto) {
    return ResponseEntity.ok(mapper.toDriverResponseDto(driverService.updateDriverNameById(id, driverRequestDto)));
  }
}
