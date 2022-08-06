package com.hcmus.wiberback.model.entity;


import java.time.LocalDateTime;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
@Builder
public class CarRequest extends BaseEntity {

  private Customer customer;
  private CallCenter callCenter;

  @NotEmpty
  @NotBlank
  private String pickingAddress;

  private String arrivingAddress;

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

  @NotNull
  @CreatedDate
  private LocalDateTime createdAt;

  @NotNull
  @LastModifiedDate
  private LocalDateTime updatedAt;
}
