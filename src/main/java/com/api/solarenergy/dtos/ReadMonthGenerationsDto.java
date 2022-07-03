package com.api.solarenergy.dtos;

public class ReadMonthGenerationsDto {
    private String month;
    private Double generatePower;

    public ReadMonthGenerationsDto(String month, Double generatePower) {
        this.month = month;
        this.generatePower = generatePower;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getGeneratePower() {
        return generatePower;
    }

    public void setGeneratePower(Double generatePower) {
        this.generatePower = generatePower;
    }
}
