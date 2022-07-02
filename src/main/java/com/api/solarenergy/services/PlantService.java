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

    @Transactional
    public Collection<PlantModel> findAll() {
        return plantRepository.findAll();
    }

    @Transactional
    public Optional<PlantModel> findById(UUID id) {
        return plantRepository.findById(id);
    }
}
