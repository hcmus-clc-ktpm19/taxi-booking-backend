package com.hcmus.wiberback.repository.mongo;

import com.hcmus.wiberback.model.entity.mongo.CarRequest;
import com.hcmus.wiberback.model.entity.mongo.Customer;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRequestRepository extends MongoRepository<CarRequest, String> {
  List<CarRequest> findCarRequestsByCustomer(Customer customer);
}
