package com.hcmus.wiberback.entity.dto;

import com.hcmus.wiberback.entity.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DriverResponseDto {
    @NotEmpty
    @NotBlank
    private String id;

    @NotEmpty
    @NotBlank
    private String name;
    @NotEmpty(message = "Phone number is required")
    @NotBlank(message = "Phone number is required")
    private String phone;
    @NotNull
    private Role role;
}
