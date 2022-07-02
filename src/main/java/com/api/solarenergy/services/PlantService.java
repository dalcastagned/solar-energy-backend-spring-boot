package com.api.solarenergy.services;

import com.api.solarenergy.models.PlantModel;
import com.api.solarenergy.repositories.PlantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
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

    public Collection<PlantModel> findAll() {
        return plantRepository.findAll();
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
