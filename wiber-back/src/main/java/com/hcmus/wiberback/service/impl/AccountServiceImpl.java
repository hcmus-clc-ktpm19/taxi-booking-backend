package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.entity.dto.AccountRequestDto;
import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.exception.AccountExistedException;
import com.hcmus.wiberback.entity.exception.AccountNotFoundException;
import com.hcmus.wiberback.repository.AccountRepository;
import com.hcmus.wiberback.service.AccountService;
import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

    return new User(user.getPhone(), user.getPassword(), authorities);
  }

  @Override
  @Cacheable(value = "account", key = "#phone")
  public Account findAccountByPhone(String phone) {
    return accountRepository.findAccountByPhone(phone)
        .orElseThrow(() -> new AccountNotFoundException("Account not found", phone));
  }

  @Override
  public String saveAccount(AccountRequestDto accountRequestDto) {
    if (accountRepository.existsByPhoneAndRole(accountRequestDto.getPhone(), accountRequestDto.getRole())) {
      throw new AccountExistedException("Account with phone and role already existed",
          accountRequestDto.getPhone(), accountRequestDto.getRole());
    }

    Account account = new Account();
    account.setPhone(accountRequestDto.getPhone());
    account.setPassword(bCryptPasswordEncoder.encode(accountRequestDto.getPassword()));
    account.setRole(accountRequestDto.getRole());

    return accountRepository.save(account).getId();
  }
}
