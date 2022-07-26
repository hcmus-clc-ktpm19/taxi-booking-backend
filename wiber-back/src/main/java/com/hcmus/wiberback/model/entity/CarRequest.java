package com.hcmus.wiberback.model.entity;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
@Builder
public class CarRequest extends BaseEntity {

  @NotNull
  private Customer customer;

  @NotEmpty
  @NotBlank
  private String pickingAddress;

  private String arrivingAddress;

  @NotNull
  private Double lngPickingAddress;

  @NotNull
  private Double latPickingAddress;

  private Double lngArrivingAddress;
  private Double latArrivingAddress;
}
