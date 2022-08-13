package com.hcmus.wiberback.repository;

import com.hcmus.wiberback.model.entity.CarRequest;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.enums.CarRequestStatus;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRequestRepository extends MongoRepository<CarRequest, String> {
  List<CarRequest> findByCustomer(Customer customer);
  List<CarRequest> findCarRequestsByCustomer(Customer customer);
  List<CarRequest> findCarRequestByPickingAddress(String pickingAddress);
  List<CarRequest> findCarRequestByStatus(CarRequestStatus status);
}
