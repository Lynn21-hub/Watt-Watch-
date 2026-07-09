package com.example.ingestion_service.service;

import com.example.ingestion_service.dto.EnergyUsageDto;
import com.example.ingestion_service.event.EnergyUsageEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class IngestionService {

    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;
    private final String topicName;

    public IngestionService(
        KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate,
        @Value("${spring.kafka.template.default-topic:energy-usage}") String topicName
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void ingestEnergyUsage(EnergyUsageDto energyUsageDto) {
        Instant eventTimestamp = energyUsageDto.timestamp() != null
            ? energyUsageDto.timestamp()
            : Instant.now();

        EnergyUsageEvent event = new EnergyUsageEvent(
            energyUsageDto.deviceId(),
            energyUsageDto.energyConsumed(),
            eventTimestamp
        );

        kafkaTemplate.send(topicName, String.valueOf(event.deviceId()), event);
    }
}