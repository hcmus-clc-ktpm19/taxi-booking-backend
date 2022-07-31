package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.model.dto.CallCenterRequestDto;
import com.hcmus.wiberback.model.dto.CallCenterResponseDto;
import com.hcmus.wiberback.service.CallCenterService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/callcenter")
public class CallCenterController extends AbstractApplicationController {
  private final CallCenterService callCenterService;

  @GetMapping("/all")
  public ResponseEntity<List<CallCenterResponseDto>> getAllCallCenter() {
    return ResponseEntity.ok(callCenterService.getAllCallCenter().stream()
        .map(mapper::toCallCenterResponseDto)
        .collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<String> saveAccount(
      @Valid @RequestBody CallCenterRequestDto callCenterRequestDto) {
    return ResponseEntity.ok(callCenterService.saveCallCenter(callCenterRequestDto));
  }
}
