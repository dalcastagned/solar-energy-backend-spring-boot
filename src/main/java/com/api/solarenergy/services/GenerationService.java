package com.api.solarenergy.services;

import com.api.solarenergy.repositories.GenerationRepository;
import org.springframework.stereotype.Service;

@Service
public class GenerationService {
    final GenerationRepository generationRepository;

    public GenerationService(GenerationRepository generationRepository) {
        this.generationRepository = generationRepository;
    }
}
