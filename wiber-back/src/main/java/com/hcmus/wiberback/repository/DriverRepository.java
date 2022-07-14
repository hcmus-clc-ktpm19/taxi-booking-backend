package com.hcmus.wiberback.repository;

import com.hcmus.wiberback.entity.entity.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepository extends MongoRepository<Driver, String> {
}
