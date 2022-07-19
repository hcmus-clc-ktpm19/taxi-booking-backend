package com.hcmus.wiberback.model.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
public class CallCenter extends BaseEntity {
  private String name;

  @DBRef
  private Account account;

}
