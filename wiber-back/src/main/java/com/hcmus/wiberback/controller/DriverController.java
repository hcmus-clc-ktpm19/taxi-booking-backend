package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.dto.DriverRequestDto;
import com.hcmus.wiberback.entity.dto.DriverResponseDto;
import com.hcmus.wiberback.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driver")
public class DriverController extends AbstractApplicationController {
    private final DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDto> getDriverById(@PathVariable String id) {
        return ResponseEntity.ok(mapper.driverToDriverResponseDto(driverService.getDriverById(id)));
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<DriverResponseDto> getDriverByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(mapper.driverToDriverResponseDto(driverService.getDriverByPhone(phone)));
    }
    @GetMapping("/all")
    public ResponseEntity<List<DriverResponseDto>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers().stream()
                .map(mapper::driverToDriverResponseDto)
                .collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<String> saveAccount(@Valid @RequestBody DriverRequestDto driverRequestDto) {
        return ResponseEntity.ok(driverService.saveDriver(driverRequestDto));
    }
}
