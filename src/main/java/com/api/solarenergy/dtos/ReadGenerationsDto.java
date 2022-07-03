package com.api.solarenergy.dtos;

import java.util.Collection;

public class ReadGenerationsDto {
    public ReadGenerationsDto(Collection<ReadGenerationDto> generations, int pageCount, int pageNumber) {
        this.generations = generations;
        this.pageCount = pageCount;
        this.pageNumber = pageNumber;
    }

    private Collection<ReadGenerationDto> generations;
    private int pageCount;
    private int pageNumber;

    public Collection<ReadGenerationDto> getGenerations() {
        return generations;
    }

    public void setGenerations(Collection<ReadGenerationDto> generations) {
        this.generations = generations;
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
