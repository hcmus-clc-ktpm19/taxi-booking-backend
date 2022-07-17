package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.dto.AccountDto;
import com.hcmus.wiberback.service.AccountService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  @PutMapping("/{id}")
    public ResponseEntity<String> updatePasswordById(@PathVariable String id, @Valid @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.updatePasswordById(id, accountDto));
    }
}
