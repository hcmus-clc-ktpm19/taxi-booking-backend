package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.dto.AccountRequestDto;
import com.hcmus.wiberback.entity.enums.Role;
import com.hcmus.wiberback.service.AccountService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AccountController extends AbstractApplicationController {

  private final AccountService accountService;
  @PostMapping("/register")
  public ResponseEntity<String> createAccount(
      @Valid @RequestBody AccountRequestDto accountRequestDto) {

    return ResponseEntity.ok(accountService.saveAccount(accountRequestDto));
  }
}
