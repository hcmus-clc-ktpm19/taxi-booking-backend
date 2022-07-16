package com.hcmus.wiberback.repository;

import com.hcmus.wiberback.model.entity.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
//    Optional<Driver> findDriverByAccount(String phone);
}
