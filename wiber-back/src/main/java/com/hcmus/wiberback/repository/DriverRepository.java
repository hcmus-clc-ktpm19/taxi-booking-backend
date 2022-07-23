package com.hcmus.wiberback.repository;

import com.hcmus.wiberback.model.entity.Account;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.entity.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
//    Optional<Driver> findDriverByAccount(String phone);
    Optional<Driver> findDriverByAccount(Account account);
    boolean existsByAccount(Account account);
}
