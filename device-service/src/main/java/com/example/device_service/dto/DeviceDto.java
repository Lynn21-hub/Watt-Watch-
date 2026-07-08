package com.example.device_service.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.example.device_service.model.DeviceType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class DeviceDto {
    private Long id;
    private String name ;
    private DeviceType type;
    private String location;
    private Long userId;
}
