package com.api.solarenergy.controllers;

import com.api.solarenergy.dtos.CreatePlantDto;
import com.api.solarenergy.dtos.ReadPlantsCounts;
import com.api.solarenergy.models.PlantModel;
import com.api.solarenergy.services.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping()
    public ResponseEntity<Collection<PlantModel>> getAllPlants(){
        return ResponseEntity.status(HttpStatus.OK).body(plantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlantById(@PathVariable("id") UUID id){
        Optional<PlantModel> plantModelOptional = plantService.findById(id);
        if(!plantModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(plantModelOptional.get());
    }

    @GetMapping("/counts")
    public ResponseEntity<ReadPlantsCounts> getPlantCounts(){
        var activePlantCount = plantService.getActivePlantCounts();
        var inactivePlantCount = plantService.getInactivePlantCounts();
        return ResponseEntity.status(HttpStatus.OK).body(new ReadPlantsCounts(activePlantCount, inactivePlantCount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlantById(@PathVariable("id") UUID id){
        Optional<PlantModel> plantModelOptional = plantService.findById(id);
        if(!plantModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        plantService.delete(plantModelOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlantById(@PathVariable("id") UUID id, @RequestBody @Valid CreatePlantDto createPlantDto){
        Optional<PlantModel> plantModelOptional = plantService.findById(id);
        if(!plantModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        var plantModel = plantModelOptional.get();
        BeanUtils.copyProperties(createPlantDto, plantModel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(plantService.save(plantModel));
    }
}
