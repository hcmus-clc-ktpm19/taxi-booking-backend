package com.hcmus.customerservice.repository;

import com.hcmus.customerservice.model.entity.Account;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

  boolean existsByPhone(String phone);

  Optional<Account> findAccountByPhone(String phone);
}
