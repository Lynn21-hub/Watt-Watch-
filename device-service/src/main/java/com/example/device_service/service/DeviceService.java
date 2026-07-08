package com.example.device_service.service;

import org.springframework.stereotype.Service;
import com.example.device_service.repository.DeviceRepository;
import com.example.device_service.entity.Device;
import com.example.device_service.dto.DeviceDto;
import com.example.device_service.model.DeviceType;

@Service
//the service layer converts the dto to entity and vice versa 

public class DeviceService {
    private final DeviceRepository devicerepo;
    //inject the repository into the service layer
    public DeviceService(DeviceRepository devicerepo){
        this.devicerepo=devicerepo;
    }

    public DeviceDto createDevice(DeviceDto deviceDto) {
        final Device createdDevice = Device.builder()
                .name(deviceDto.getName())
                .type(deviceDto.getType().toString())
                .location(deviceDto.getLocation())
                .userId(deviceDto.getUserId())
                .build();
        Device saved = devicerepo.save(createdDevice);
        return convertToDto(saved);
    }
    public DeviceDto getDeviceById(Long id){
        Device device = devicerepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Device not found with id " + id));
        return convertToDto(device);
    }

    public DeviceDto patchDevice(Long id, DeviceDto device){
        Device existingDevice = devicerepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Device not found with id " + id));

        if (device.getName() != null) {
            existingDevice.setName(device.getName());
        }
        if (device.getType() != null) {
            existingDevice.setType(device.getType().name());
        }
        if (device.getLocation() != null) {
            existingDevice.setLocation(device.getLocation());
        }
        if (device.getUserId() != null) {
            existingDevice.setUserId(device.getUserId());
        }

        Device updatedDevice = devicerepo.save(existingDevice);
        return convertToDto(updatedDevice);
    }
    public void deleteDevice(Long id){
        if(!devicerepo.existsById(id)){
            throw new IllegalArgumentException("Device not found with id "+ id);
        }
        devicerepo.deleteById(id);
    }

    private DeviceDto convertToDto(Device device) {
        return DeviceDto.builder()
                .id(device.getId())
                .name(device.getName())
                .type(DeviceType.valueOf(device.getType()))
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }


}
