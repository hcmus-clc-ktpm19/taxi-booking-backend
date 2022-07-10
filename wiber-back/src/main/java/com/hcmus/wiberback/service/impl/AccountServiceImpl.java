package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.entity.dto.AccountRequestDto;
import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.enums.Role;
import com.hcmus.wiberback.entity.exception.AccountNotFoundException;
import com.hcmus.wiberback.entity.exception.AccountExistedException;
import com.hcmus.wiberback.repository.AccountRepository;
import com.hcmus.wiberback.service.AccountService;
import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
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
    authorities.add(new SimpleGrantedAuthority(Role.ADMIN.name()));

    return new User(user.getPhone(), user.getPassword(), authorities);
  }

  @Override
  public Account findAccountByPhone(String phone) {
    return accountRepository.findAccountByPhone(phone)
        .orElseThrow(() -> new AccountNotFoundException("Account not found", phone));
  }

  @Override
  public String saveAccount(AccountRequestDto accountRequestDto) {
    if (accountRepository.existsByPhone(accountRequestDto.getPhone())) {
      throw new AccountExistedException("Account with phone already exists", accountRequestDto.getPhone());
    }

    Account account = new Account();
    account.setPhone(accountRequestDto.getPhone());
    account.setPassword(bCryptPasswordEncoder.encode(accountRequestDto.getPassword()));

    accountRepository.save(account);

    return account.getId();
  }
}
