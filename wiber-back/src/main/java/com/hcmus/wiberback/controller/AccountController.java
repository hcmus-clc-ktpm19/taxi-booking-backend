package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.dto.AccountDto;
import com.hcmus.wiberback.service.AccountService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AccountController extends AbstractApplicationController {

  private final AccountService accountService;

  @GetMapping
  public ResponseEntity<AccountDto> findAccountByPhone(@RequestParam String q) {
    return ResponseEntity.ok(mapper.toAccountDto(accountService.findAccountByPhone(q)));
  }

  @PostMapping("/register")
  public ResponseEntity<String> createAccount(
      @Valid @RequestBody AccountDto accountDto) {

    return ResponseEntity.ok(accountService.saveAccount(accountDto));
  }
}
