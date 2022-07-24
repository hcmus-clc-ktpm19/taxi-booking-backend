package com.hcmus.wiberback.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarRequestDto {
  private String id;
  private String customerId;
  private String arrivingAddress;
  private Double lngArrivingAddress;
  private Double latArrivingAddress;

  @NotEmpty
  @NotBlank
  @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
  private String customerPhone;

  @NotEmpty
  @NotBlank
  private String pickingAddress;

  @NotNull
  private Double lngPickingAddress;

  @NotNull
  private Double latPickingAddress;
}
