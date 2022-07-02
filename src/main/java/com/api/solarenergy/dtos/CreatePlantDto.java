package com.api.solarenergy.dtos;

import javax.validation.constraints.NotBlank;

public class CreatePlantDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String place;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    private Boolean active;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
