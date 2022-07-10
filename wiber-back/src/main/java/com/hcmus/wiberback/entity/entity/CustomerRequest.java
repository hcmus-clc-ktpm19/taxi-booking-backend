package com.hcmus.wiberback.entity.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
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
