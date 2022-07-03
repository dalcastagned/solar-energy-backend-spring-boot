package com.api.solarenergy.controllers;

import com.api.solarenergy.dtos.CreateGenerationDto;
import com.api.solarenergy.dtos.ReadGenerationDto;
import com.api.solarenergy.models.GenerationModel;
import com.api.solarenergy.services.GenerationService;
import com.api.solarenergy.services.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/plant")
public class GenerationController {
    final GenerationService generationService;
    final PlantService plantService;

    public GenerationController(GenerationService generationService, PlantService plantService) {
        this.generationService = generationService;
        this.plantService = plantService;
    }

    @PostMapping("/{plantId}/generation")
    public ResponseEntity<Object> createGeneration(@PathVariable("plantId") UUID plantId, @RequestBody @Valid CreateGenerationDto createGenerationDto){
        var plantModel = plantService.findById(plantId);
        if (!plantService.findById(plantId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        var generationModel = new GenerationModel();
        BeanUtils.copyProperties(createGenerationDto, generationModel);
        generationModel.setPlant(plantModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(generationService.save(generationModel));
    }

    @GetMapping("/{plantId}/generation/{generationId}")
    public ResponseEntity<Object> getGenerationById(@PathVariable("plantId") UUID plantId, @PathVariable("generationId") UUID generationId){
        var plantModel = plantService.findById(plantId);
        if (!plantService.findById(plantId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        var generationModel = generationService.findById(generationId);
        if (!generationService.findById(generationId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Generation not found");
        }
        var generationDto = new ReadGenerationDto();
        BeanUtils.copyProperties(generationModel.get(), generationDto);
        return ResponseEntity.status(HttpStatus.OK).body(generationDto);
    }

    @DeleteMapping("/{plantId}/generation/{generationId}")
    public ResponseEntity<Object> deleteGenerationById(@PathVariable("plantId") UUID plantId, @PathVariable("generationId") UUID generationId){
        var plantModel = plantService.findById(plantId);
        if (!plantService.findById(plantId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        var generationModel = generationService.findById(generationId);
        if (!generationService.findById(generationId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Generation not found");
        }
        generationService.delete(generationModel.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
