package com.hcmus.wiberback.model.entity;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
public class CustomerRequest extends BaseEntity {
  private Customer customer;
  private String pickingAddress;
  private String arrivingAddress;
  private Object lngPickingAddress;
  private Object latPickingAddress;
  private Object lngArrivingAddress;
  private Object latArrivingAddress;
}
