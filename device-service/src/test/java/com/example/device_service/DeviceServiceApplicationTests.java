package com.example.device_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.device_service.entity.Device;
import com.example.device_service.model.DeviceType;
import com.example.device_service.repository.DeviceRepository;

@SpringBootTest
class DeviceServiceApplicationTests {
	public static final int USERS = 10;
	@Autowired
	private DeviceRepository deviceRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void createDevices(){
		for(int i=0;i<=200;i++){
			Device device = Device.builder()
				.name("Device "+i)
				.type(DeviceType.values()[i%DeviceType.values().length].name())
				.location("Location "+(i%3+1))
				.userId((long) (i% USERS)+1)
				.build();
			deviceRepository.save(device);
		}
	}

}
