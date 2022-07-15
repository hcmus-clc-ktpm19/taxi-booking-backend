package com.hcmus.wiberback.entity.enums;

public enum WiberUrl {
  LOGIN_URL("/api/v1/auth/login"),
  REGISTER_URL("/api/v1/auth/register"),
  CUSTOMER_URL("/api/v1/customer/**"),
  DRIVER_URL("/api/v1/driver/**");

  public final String url;

  WiberUrl(String url) {
    this.url = url;
  }
}
