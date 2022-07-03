package com.api.solarenergy.dtos;

import java.time.LocalDateTime;

public class ReadGenerationDto {
    private LocalDateTime date;
    private Double generatePower;

    public LocalDateTime getDate() {
        return date;
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
