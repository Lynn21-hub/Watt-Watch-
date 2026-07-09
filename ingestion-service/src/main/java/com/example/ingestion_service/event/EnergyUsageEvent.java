package com.example.ingestion_service.event;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record EnergyUsageEvent(
    Long deviceId,
    Double energyUsage,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Instant timestamp
) {}