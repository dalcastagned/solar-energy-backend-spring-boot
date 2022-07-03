package com.api.solarenergy.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateGenerationDto {
    @NotNull(message = "Date is required")
    private LocalDateTime date;
    @NotNull(message = "Generate power is required")
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
