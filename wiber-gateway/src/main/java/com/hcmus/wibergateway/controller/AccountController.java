package com.hcmus.wibergateway.controller;

import com.hcmus.customerservice.model.dto.AccountDto;
import com.hcmus.customerservice.service.AccountService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class AccountController extends AbstractApplicationController {

  private final AccountService accountService;
  private final UserDetailsService userDetailsService;

  @GetMapping("/user-details")
  public ResponseEntity<UserDetails> getUserDetails(@RequestParam String phone) {
    return ResponseEntity.ok(userDetailsService.loadUserByUsername(phone));
  }

  @GetMapping
  public ResponseEntity<AccountDto> getAccount(@RequestParam(required = false) String phone) {
    return ResponseEntity.ok(mapper.accountToAccountDto(accountService.findAccountByPhone(phone)));
  }

  @PostMapping("/register")
  public ResponseEntity<String> createAccount(@Valid @RequestBody AccountDto accountDto) {
    return ResponseEntity.ok(accountService.saveAccount(accountDto));
  }
}
