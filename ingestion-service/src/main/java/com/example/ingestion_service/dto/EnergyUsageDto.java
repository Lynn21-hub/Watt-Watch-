package com.example.ingestion_service.dto;

import lombok.Builder;

import java.time.Instant;
@Builder
public record EnergyUsageDto(
    Long deviceId,
    double energyConsumed,
    Instant timestamp
) {}