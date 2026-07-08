package com.example.device_service.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.example.device_service.model.DeviceType;

import jakarta.persistence.Column;

@Entity 
@Table(name = "device")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data 
public class Device {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="type")
    private String type;

    private String location;
    private Long userId;
}
