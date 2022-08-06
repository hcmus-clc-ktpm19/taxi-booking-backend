package com.hcmus.wiberback.model.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
