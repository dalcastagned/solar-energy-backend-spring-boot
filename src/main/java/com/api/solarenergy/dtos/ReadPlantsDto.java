package com.api.solarenergy.dtos;

import java.util.Collection;

public class ReadPlantsDto {
    public ReadPlantsDto(Collection<ReadPlantDto> plants, int pageCount, int pageNumber) {
        this.plants = plants;
        this.pageCount = pageCount;
        this.pageNumber = pageNumber;
    }

    private Collection<ReadPlantDto> plants;
    private int pageCount;
    private int pageNumber;

    public Collection<ReadPlantDto> getPlants() {
        return plants;
    }

    public void setPlants(Collection<ReadPlantDto> plants) {
        this.plants = plants;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
