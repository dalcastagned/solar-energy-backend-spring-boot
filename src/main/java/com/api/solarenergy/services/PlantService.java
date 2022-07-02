package com.api.solarenergy.services;

import com.api.solarenergy.models.PlantModel;
import com.api.solarenergy.repositories.PlantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlantService {
    final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Transactional
    public UUID save(PlantModel plantModel) {
        return plantRepository.save(plantModel).getId();
    }

    public Page<PlantModel> findAll(Pageable pageable, String filter) {
        if (filter == null) {
            return plantRepository.findAll(pageable);
        } else {
            return plantRepository.findAllPaginatedAndFiltered(filter.toLowerCase(), pageable);
        }
    }

    public Optional<PlantModel> findById(UUID id) {
        return plantRepository.findById(id);
    }

    public int getActivePlantCounts() {
        return plantRepository.countByActive(true);
    }

    public int getInactivePlantCounts() {
        return plantRepository.countByActive(false);
    }

    @Transactional
    public void delete(PlantModel plantModel) {
        plantRepository.delete(plantModel);
    }
}
