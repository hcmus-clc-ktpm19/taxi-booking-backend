package com.hcmus.socketservice.model.dto;


import com.hcmus.socketservice.model.enums.CarRequestStatus;
import com.hcmus.socketservice.model.enums.CarType;
import com.hcmus.socketservice.model.enums.SenderServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
  private SenderServiceType senderServiceType;
}
