package com.hcmus.wiberback.model.dto;

import com.hcmus.wiberback.model.enums.CarRequestStatus;
import com.hcmus.wiberback.model.enums.Role;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallCenterCarRequestDto {
  @NotEmpty
  @NotBlank
  private String id;

  @NotEmpty
  @NotBlank
  private String customerName;

  @NotEmpty
  @NotBlank
  @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
  private String customerPhone;

  @NotEmpty
  @NotBlank
  private String pickingAddress;
}