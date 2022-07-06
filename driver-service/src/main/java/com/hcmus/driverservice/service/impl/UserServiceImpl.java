package com.hcmus.customerservice.service.impl;

import com.hcmus.customerservice.model.entity.User;
import com.hcmus.customerservice.service.UserService;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO: This if for testing, remove it later
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    User user = new User("admin", bCryptPasswordEncoder.encode("admin"));

    return new org.springframework.security.core.userdetails.User(user.getPhone(),
        user.getPassword(), new HashSet<>());
  }
}
