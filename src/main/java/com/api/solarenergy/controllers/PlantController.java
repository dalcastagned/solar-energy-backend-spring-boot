package com.api.solarenergy.controllers;

import com.api.solarenergy.dtos.CreatePlantDto;
import com.api.solarenergy.dtos.ReadPlantDto;
import com.api.solarenergy.dtos.ReadPlantCountsDto;
import com.api.solarenergy.dtos.ReadPlantsDto;
import com.api.solarenergy.models.PlantModel;
import com.api.solarenergy.services.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ResponseEntity<ReadPlantsDto> getAllPlants(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(required = false) String filter){
        var plants = plantService.findAll(pageable, filter);
        var plantsDto = plants.getContent().stream().map(plant -> {
            var plantDto = new ReadPlantDto();
            BeanUtils.copyProperties(plant, plantDto);
            return plantDto;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new ReadPlantsDto(plantsDto, plants.getTotalPages(), plants.getNumber() + 1));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlantById(@PathVariable("id") UUID id){
        Optional<PlantModel> plantModelOptional = plantService.findById(id);
        if(!plantModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        var readPlantDto = new ReadPlantDto();
        BeanUtils.copyProperties(plantModelOptional.get(), readPlantDto);
        return ResponseEntity.status(HttpStatus.OK).body(readPlantDto);
    }

    @GetMapping("/counts")
    public ResponseEntity<ReadPlantCountsDto> getPlantCounts(){
        var activePlantCount = plantService.getActivePlantCounts();
        var inactivePlantCount = plantService.getInactivePlantCounts();
        return ResponseEntity.status(HttpStatus.OK).body(new ReadPlantCountsDto(activePlantCount, inactivePlantCount));
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
