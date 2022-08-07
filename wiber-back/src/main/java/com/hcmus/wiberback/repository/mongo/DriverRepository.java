package com.hcmus.wiberback.repository.mongo;

import com.hcmus.wiberback.model.entity.mongo.Account;
import com.hcmus.wiberback.model.entity.mongo.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
//    Optional<Driver> findDriverByAccount(String phone);
    Optional<Driver> findDriverByAccount(Account account);
    boolean existsByAccount(Account account);
}
