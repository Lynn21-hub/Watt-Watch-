package com.example.ingestion_service.simultion;

import java.time.Instant;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;

import com.example.ingestion_service.dto.EnergyUsageDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ContiniousDataSimulator implements CommandLineRunner {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    @Value("${simulation.requests-per-interval}")
    private int reqPerInterval ; // Example value, adjust as needed

    @Value("${simulation.ingestion-endpoint}")
    private String ingestionEndpoint;

    @Override 
    public void run(String... args) throws Exception {
        log.info("Starting continuous data simulation...");

    }
    @Scheduled(fixedRateString = "${simulation.interval}")
    public void sendMockData(){
        for(int i = 0; i < reqPerInterval; i++){
            EnergyUsageDto dto = EnergyUsageDto.builder()
                .deviceId(random.nextLong(1, 6))
                .energyConsumed(random.nextDouble() * 100)
                .timestamp(Instant.now())
                .build();
            try{
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<EnergyUsageDto> request = new HttpEntity<>(dto, headers); //wrap dto into an http request 
                restTemplate.postForEntity(ingestionEndpoint, request, Void.class);//send post request to ingestion endpoint
                
            } catch(Exception e) {
                log.error("Error sending mock data", e);
            }

        }
    }

}
