package com.hcmus.wiberback.model.entity.mongo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
public class Customer extends BaseEntity {
  @NotEmpty
  @NotBlank
  private String name;

  private String phone;

  @DBRef
  private Account account;
}