package com.hcmus.wiberback.entity.entity;

import com.hcmus.wiberback.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
public class Customer extends Account {
  private String name;
  private Role role = Role.CUSTOMER;
}
