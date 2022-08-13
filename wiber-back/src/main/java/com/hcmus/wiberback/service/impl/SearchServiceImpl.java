package com.hcmus.wiberback.service.impl;

import com.hcmus.wiberback.model.entity.Account;
import com.hcmus.wiberback.model.entity.CarRequest;
import com.hcmus.wiberback.model.entity.Customer;
import com.hcmus.wiberback.model.enums.CarRequestStatus;
import com.hcmus.wiberback.model.exception.UserNotFoundException;
import com.hcmus.wiberback.service.SearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<CarRequest> searchAddress(String phone, String address) {
    log.info("Searching address by phone: {}", phone);
    Customer customer;

    Query findCustomerQuery = Query.query(Criteria.where("phone").is(phone));
    List<Account> accounts = mongoTemplate.find(findCustomerQuery, Account.class, "account");
    List<Customer> customers;
    if (accounts.isEmpty()) {
      customers = mongoTemplate.find(findCustomerQuery, Customer.class, "customer");
    } else {
      Account account = accounts.get(0);

      customers = mongoTemplate
          .find(Query.query(Criteria.where("account.$id").is(new ObjectId(account.getId()))),
              Customer.class, "customer");
    }

    if (customers.isEmpty()) {
      throw new UserNotFoundException("Customer not found", phone);
    }
    customer = customers.get(0);

    Query query = TextQuery
        .queryText(TextCriteria.forDefaultLanguage().matchingAny(address))
        .sortByScore()
        .addCriteria(Criteria.where("customer.$id").is(new ObjectId(customer.getId()))
                .and("status").is(CarRequestStatus.FINISHED))
        .with(Sort.by(Direction.DESC, "createdAt"))
        .limit(5);

    return mongoTemplate.find(query, CarRequest.class, "carRequest");
  }
}
