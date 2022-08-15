package com.hcmus.wiberback.repository.custom.impl;

import com.hcmus.wiberback.model.entity.Account;
import com.hcmus.wiberback.model.entity.CarRequest;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.enums.CarRequestStatus;
import com.hcmus.wiberback.model.exception.UserNotFoundException;
import com.hcmus.wiberback.repository.custom.CarRequestRepositoryCustom;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarRequestRepositoryCustomImpl implements CarRequestRepositoryCustom {
  private final MongoTemplate mongoTemplate;

  @Override
  public List<CarRequest> getCarRequestsByPhoneAndStatus(String phone, CarRequestStatus status) {
    Customer customer;

    Query findCustomerQuery = Query.query(Criteria.where("phone").is(phone));
    List<Account> accounts = mongoTemplate.find(findCustomerQuery, Account.class, "account");
    if (accounts.isEmpty()) {
      throw new UserNotFoundException("Customer not found", phone);
    }

    Account account = accounts.get(0);

    List<Customer> customers = mongoTemplate
        .find(Query.query(
            Criteria
                .where("account.$id")
                .is(new ObjectId(account.getId()))),
            Customer.class,
            "customer");

    if (customers.isEmpty()) {
      throw new UserNotFoundException("Customer not found", phone);
    }
    customer = customers.get(0);

    return mongoTemplate.find(Query.query(
            Criteria
                .where("customer.$id")
                .is(new ObjectId(customer.getId()))
                .and("status")
                .is(status)),
        CarRequest.class,
        "carRequest");
  }
}
