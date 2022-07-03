package com.api.solarenergy.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReadGenerationDto {
    private UUID id;
    private LocalDateTime date;
    private Double generatePower;

    public LocalDateTime getDate() {
        return date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getGeneratePower() {
        return generatePower;
    }

    public void setGeneratePower(Double generatePower) {
        this.generatePower = generatePower;
    }
}
