package com.hcmus.wiberback.repository;

import com.hcmus.wiberback.model.entity.CallCenter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CallCenterRepository extends MongoRepository<CallCenter, String> {

}
