package com.api.solarenergy.services;

import com.api.solarenergy.dtos.ReadGenerationDto;
import com.api.solarenergy.models.GenerationModel;
import com.api.solarenergy.models.PlantModel;
import com.api.solarenergy.repositories.GenerationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class GenerationService {
    final GenerationRepository generationRepository;

    public GenerationService(GenerationRepository generationRepository) {
        this.generationRepository = generationRepository;
    }

    public Page<GenerationModel> findAll(UUID plantId, Pageable pageable, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null && endDate == null) {
            return generationRepository.findAllByPlantId(plantId, pageable);
        }

        if (startDate != null && endDate == null) {
            return generationRepository.findAllByPlantIdAndGreaterThanStartDate(plantId, startDate, pageable);
        }

        if (startDate == null && endDate != null) {
            return generationRepository.findAllByPlantIdAndLessThanEndDate(plantId, endDate, pageable);
        }

        return generationRepository.findAllByPlantIdAndBetweenStartDateAndEndDate(plantId, startDate, endDate, pageable);
    }
    @Transactional
    public UUID save(GenerationModel generationModel) {
        return generationRepository.save(generationModel).getId();
    }

    public Optional<GenerationModel> findById(UUID id) {
        return generationRepository.findById(id);
    }

    @Transactional
    public void delete(GenerationModel generationModel) {
        generationRepository.delete(generationModel);
    }

    public Collection<GenerationModel> findAllByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return generationRepository.findAllByDate(startDate, endDate);
    }
}
