package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.entity.dto.AccountRequestDto;
import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.exception.AccountNotFoundException;
import com.hcmus.wiberback.entity.exception.ExistedAccountException;
import com.hcmus.wiberback.repository.AccountRepository;
import com.hcmus.wiberback.service.AccountService;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, UserDetailsService {

  private final AccountRepository accountRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account user = accountRepository.findAccountByPhone(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("Account not found with username: " + username));

    return new User(user.getPhone(), user.getPassword(), new HashSet<>());
  }

  @Override
  public Account findAccountByPhone(String phone) {
    return accountRepository.findAccountByPhone(phone)
        .orElseThrow(() -> new AccountNotFoundException("Account not found", phone));
  }

  @Override
  public String saveAccount(AccountRequestDto accountRequestDto) {
    if (accountRepository.existsByPhone(accountRequestDto.getPhone())) {
      throw new ExistedAccountException("Account with phone already exists", accountRequestDto.getPhone());
    }

    Account account = new Account();
    account.setPhone(accountRequestDto.getPhone());
    account.setPassword(bCryptPasswordEncoder.encode(accountRequestDto.getPassword()));

    accountRepository.save(account);

    return account.getId();
  }
}
