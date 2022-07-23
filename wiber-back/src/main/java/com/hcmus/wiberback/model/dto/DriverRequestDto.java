package com.hcmus.wiberback.model.dto;

import com.hcmus.wiberback.model.enums.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class DriverRequestDto {
    private String id;

    @NotEmpty(message = "Phone number is required")
    @NotBlank(message = "Phone number is required")
    @Digits(integer = 10, fraction = 0)
    private String phone;

    @NotEmpty(message = "Name number is required")
    @NotBlank(message = "Name number is required")
    private String name;

    private Role role;
}
