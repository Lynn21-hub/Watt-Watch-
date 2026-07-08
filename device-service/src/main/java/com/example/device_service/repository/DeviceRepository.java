package com.example.device_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.device_service.entity.Device;


public interface DeviceRepository extends JpaRepository<Device, Long> {

}
