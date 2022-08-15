package com.hcmus.smsservice.model.dto;

import com.hcmus.smsservice.model.enums.CarRequestStatus;
import com.hcmus.smsservice.model.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequestDto {
  private String id;
  private String customerId;
  private String customerPhone;
  private String driverId;
  private String driverName;
  private String driverPhone;
  private String pickingAddress;
  private String arrivingAddress;
  private Double lngArrivingAddress;
  private Double latArrivingAddress;
  private Double lngPickingAddress;
  private Double latPickingAddress;
  private CarType carType;
  private CarRequestStatus status;
  private Double price;
  private Double distance;
}