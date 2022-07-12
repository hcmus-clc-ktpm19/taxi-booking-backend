package com.hcmus.wiberback.repository;

import com.hcmus.wiberback.entity.entity.Account;
import com.hcmus.wiberback.entity.enums.Role;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

  boolean existsByPhoneAndRole(String phone, Role role);
  Optional<Account> findAccountByPhone(String phone);
}
