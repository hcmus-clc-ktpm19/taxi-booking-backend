package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.dto.AccountRequestDto;
import com.hcmus.wiberback.service.AccountService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends AbstractApplicationController {

  private final AccountService accountService;
  private final UserDetailsService userDetailsService;

  @PostMapping("/register")
  public ResponseEntity<String> createAccount(@Valid @RequestBody AccountRequestDto accountRequestDto) {
    return ResponseEntity.ok(accountService.saveAccount(accountRequestDto));
  }
}
