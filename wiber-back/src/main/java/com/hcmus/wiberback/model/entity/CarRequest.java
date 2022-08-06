package com.hcmus.wiberback.model.entity;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
@Builder
public class CarRequest extends BaseEntity {

  @NotNull
  @DBRef
  private Customer customer;
  @DBRef
  private CallCenter callCenter;

  @NotEmpty
  @NotBlank
  private String pickingAddress;

  private String arrivingAddress;

  // TODO : should be not null
//  @NotNull
  private Double lngPickingAddress;

//  @NotNull
  private Double latPickingAddress;

  private Double lngArrivingAddress;
  private Double latArrivingAddress;
  private String status;
  @NotEmpty
  @NotBlank
  private String carType;

  @CreatedDate
  private DateTime createdAt;

  @LastModifiedDate
  private DateTime updatedAt;
}
