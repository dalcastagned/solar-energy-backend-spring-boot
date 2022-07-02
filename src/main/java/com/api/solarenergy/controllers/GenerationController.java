package com.api.solarenergy.controllers;

import com.api.solarenergy.services.GenerationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/plant")
public class GenerationController {
    final GenerationService generationService;

    public GenerationController(GenerationService generationService) {
        this.generationService = generationService;
    }
}
