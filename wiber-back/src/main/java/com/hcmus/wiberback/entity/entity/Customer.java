package com.hcmus.wiberback.entity.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {
  private String name;

  @DBRef
  private Account account;
}
