package com.hcmus.socketservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequestDto {
  private String id;
  private String customerId;
  private String customerPhone;
  private String pickingAddress;
  private String arrivingAddress;
  private Double lngArrivingAddress;
  private Double latArrivingAddress;
  private Double lngPickingAddress;
  private Double latPickingAddress;
  private String status;
}
