package com.hcmus.wiberback.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
public class Driver extends BaseEntity {

  private String name;
  @DBRef
  private Account account;
}
