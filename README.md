# WattWatch

Distributed energy telemetry and observability platform for monitoring simulated home energy consumption in real time using Spring Boot, Kafka, Docker, InfluxDB, and Grafana. Processes telemetry events through an event-driven pipeline, stores time-series metrics, detects anomalies, and exposes analytics through scalable backend services.

# Architecture Overview 
<img width="1017" height="969" alt="archVersion1" src="https://github.com/user-attachments/assets/ee320e38-4ec7-4086-946f-15a403bf6ce7" />

## Project Goals

This project was created to learn and demonstrate:

- Spring Boot microservices
- Event-driven architectures
- Apache Kafka
- Time-series data storage
- Observability and monitoring
- Docker-based deployments
- Distributed system fundamentals

## Telemetry Flow

1. Simulated devices generate energy consumption readings.
2. The Telemetry Simulator sends readings to the Ingestion Service.
3. The Ingestion Service publishes telemetry events to Kafka.
4. The Analytics Service consumes events and computes metrics.
5. Metrics are stored in InfluxDB.
6. Grafana visualizes historical and real-time energy usage.
7. Prometheus collects operational metrics from platform services.

## Services

### Telemetry Simulator
Generates simulated energy consumption readings.

### Ingestion Service
Receives telemetry events and publishes them to Kafka.

### Analytics Service
Processes telemetry events and computes energy metrics.

### Alert Service
Detects abnormal energy usage and generates alerts.

### InfluxDB
Stores time-series energy metrics.

### Grafana
Visualizes energy consumption dashboards.

### Prometheus
Collects operational metrics from platform services.
