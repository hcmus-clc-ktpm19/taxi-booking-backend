package com.hcmus.wiberback.repository;

import com.hcmus.wiberback.entity.entity.Customer;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
  Optional<Customer> findCustomerByPhone(String phone);
}
