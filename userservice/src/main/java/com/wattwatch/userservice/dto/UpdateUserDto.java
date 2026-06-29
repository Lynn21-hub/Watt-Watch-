package com.wattwatch.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    private String name;
    private String surname;
    private String email;
    private String address;
    private Boolean alerting;
    private Double alertThreshold;
}
