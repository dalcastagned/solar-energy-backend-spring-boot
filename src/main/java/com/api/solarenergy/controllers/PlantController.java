package com.api.solarenergy.controllers;

import com.api.solarenergy.dtos.CreatePlantDto;
import com.api.solarenergy.models.PlantModel;
import com.api.solarenergy.services.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/plant")
public class PlantController {
    final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping()
    public ResponseEntity<Object> createPlant(@RequestBody @Valid CreatePlantDto createPlantDto){
        var plantModel = new PlantModel();
        BeanUtils.copyProperties(createPlantDto, plantModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(plantService.save(plantModel));
    }
}
