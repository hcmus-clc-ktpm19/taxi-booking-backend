package com.hcmus.wiberback.repository.mongo;

import com.hcmus.wiberback.model.entity.mongo.Account;
import com.hcmus.wiberback.model.entity.mongo.Customer;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

  Optional<Customer> findCustomerByAccount(Account account);
  Optional<Customer> findCustomerByPhone(String phone);
  boolean existsByAccount(Account account);
}
