package com.hcmus.socketservice.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestDto {

  private String id;
  private String customerId;
  private String pickingAddress;
  private String arrivingAddress;
  private Double lngPickingAddress;
  private Double latPickingAddress;
  private Double lngArrivingAddress;
  private Double latArrivingAddress;
}
