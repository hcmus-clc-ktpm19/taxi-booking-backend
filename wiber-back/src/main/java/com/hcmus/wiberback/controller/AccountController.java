package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.model.dto.AccountDto;
import com.hcmus.wiberback.service.AccountService;
import com.hcmus.wiberback.util.JwtUtil;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AccountController extends AbstractApplicationController {

  private final AccountService accountService;

  private final AuthenticationManager authenticationManager;

  private final JwtUtil jwtUtil;


  @GetMapping
  public ResponseEntity<AccountDto> findAccountByPhone(@RequestParam String q) {
    return ResponseEntity.ok(mapper.toAccountDto(accountService.findAccountByPhone(q)));
  }

  @PostMapping("/signin")
  public ResponseEntity<?> login(@Valid @RequestBody AccountDto accountDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(accountDto.getPhone(), accountDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    User user = (User) authentication.getPrincipal();
    String accessToken = jwtUtil.generateAccessToken(user,"http://localhost:8080/api/v1/auth/signin");
    String refreshToken = jwtUtil.generateRefreshToken(user.getUsername(),"http://localhost:8080/api/v1/auth/signin");
    Map<String, String> tokens = Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    return ResponseEntity.ok(tokens);
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
