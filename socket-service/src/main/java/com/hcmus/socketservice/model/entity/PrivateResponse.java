package com.hcmus.socketservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * com.hcmus.socketservice.model.entity taxi-booking-backend Created by 19127640 - Hoang Huu Giap
 * Date 8/3/2022 - 12:30 AM Description: ...
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivateResponse {
  private Double latDriver;
  private Double lngDriver;
  private String message;
}
