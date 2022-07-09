package com.hcmus.wiberback.entity.entity;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class BaseEntity {
  @Id
  @NotNull
  protected String id;
}
