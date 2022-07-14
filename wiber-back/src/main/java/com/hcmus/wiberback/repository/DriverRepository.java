package com.hcmus.wiberback.repository;

import com.hcmus.wiberback.entity.entity.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DriverRepository extends MongoRepository<Driver, String> {
//    Optional<Driver> findDriverByAccount(String phone);
}
