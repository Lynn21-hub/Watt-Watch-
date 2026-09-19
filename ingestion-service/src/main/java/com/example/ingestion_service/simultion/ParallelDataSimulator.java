package com.example.ingestion_service.simultion;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.example.ingestion_service.dto.EnergyUsageDto;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import jakarta.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


@Slf4j
@Component



public class ParallelDataSimulator implements CommandLineRunner {
    // we will implement multi-threaded data simulation 
    private final ExecutorService executorService ; // Example thread pool size, adjust as needed
    private final Random random = new Random();
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${simulation.parallel-threads:5}")
    private int parallelThreads;

    @Value("${simulation.requests-per-interval:100}")
    private int requestsPerInterval;

     @Value("${simulation.endpoint}")
    private String ingestionEndpoint;

    public ParallelDataSimulator() {
        this.executorService = Executors.newCachedThreadPool(); // Example thread pool size, adjust as needed
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("Starting parallel data simulation...");
        ((ThreadPoolExecutor) executorService).setCorePoolSize(parallelThreads); // Set the core pool size to 5
    }

    @Scheduled(fixedRateString = "${simulation.interval}")
    public void sendMockData() {
        int batchSize = requestsPerInterval / parallelThreads;
        int remainder = requestsPerInterval % parallelThreads;

        for (int i = 0; i < parallelThreads; i++) {
            int requestsForThread = batchSize + (i < remainder ? 1 : 0);
            executorService.submit(() -> {
                for (int j = 0; j < requestsForThread; j++) {
                    EnergyUsageDto dto = EnergyUsageDto.builder()
                            .deviceId(random.nextLong(1, 200))
                            .energyConsumed(Math.round(random.nextDouble(0.0, 2.0) * 100.0) / 100.0)
                            .timestamp(LocalDateTime.now()
                                    .atZone(ZoneId.systemDefault()).toInstant())
                            .build();
                    try {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        HttpEntity<EnergyUsageDto> request = new HttpEntity<>(dto, headers);
                        restTemplate.postForEntity(ingestionEndpoint, request, Void.class);
                        log.info("Sent mock data: " + dto);
                    } catch (Exception e) {
                        log.error("Failed to send data: " + e.getMessage());
                    }
                }
            });
        }
    }
    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
        log.info("Parallel data simulation stopped.");
    }

}

