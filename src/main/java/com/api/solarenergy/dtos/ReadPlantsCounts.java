package com.api.solarenergy.dtos;

public class ReadPlantsCounts {
    public ReadPlantsCounts(int activePlants, int inactivePlants) {
        this.activePlants = activePlants;
        this.inactivePlants = inactivePlants;
    }
    private int activePlants;
    private int inactivePlants;

    public int getActivePlants() {
        return activePlants;
    }

    public void setActivePlants(int activePlants) {
        this.activePlants = activePlants;
    }

    public int getInactivePlants() {
        return inactivePlants;
    }

    public void setInactivePlants(int inactivePlants) {
        this.inactivePlants = inactivePlants;
    }
}
