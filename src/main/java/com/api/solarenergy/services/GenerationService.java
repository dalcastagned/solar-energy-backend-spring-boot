package com.api.solarenergy.services;

import com.api.solarenergy.models.GenerationModel;
import com.api.solarenergy.repositories.GenerationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class GenerationService {
    final GenerationRepository generationRepository;

    public GenerationService(GenerationRepository generationRepository) {
        this.generationRepository = generationRepository;
    }
    @Transactional
    public UUID save(GenerationModel generationModel) {
        return generationRepository.save(generationModel).getId();
    }

    public Optional<GenerationModel> findById(UUID id) {
        return generationRepository.findById(id);
    }
}
