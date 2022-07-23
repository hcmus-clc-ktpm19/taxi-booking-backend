package com.hcmus.wiberback.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
