package com.api.solarenergy.controllers;

import com.api.solarenergy.dtos.CreateGenerationDto;
import com.api.solarenergy.dtos.ReadGenerationDto;
import com.api.solarenergy.dtos.ReadGenerationsDto;
import com.api.solarenergy.dtos.ReadMonthGenerationsDto;
import com.api.solarenergy.models.GenerationModel;
import com.api.solarenergy.services.GenerationService;
import com.api.solarenergy.services.PlantService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping("/{plantId}/generation")
    public ResponseEntity<Object> getAllGenerations(@PathVariable("plantId") UUID plantId, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate){
        var plantModel = plantService.findById(plantId);
        if (!plantService.findById(plantId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        var generations = generationService.findAll(plantId, pageable, startDate, endDate);
        var generationsDto = generations.getContent().stream().map(generation -> {
            var generationDto = new ReadGenerationDto();
            BeanUtils.copyProperties(generation, generationDto);
            return generationDto;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new ReadGenerationsDto(generationsDto, generations.getTotalPages(), generations.getNumber() + 1));
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

    @PutMapping("/{plantId}/generation/{generationId}")
    public ResponseEntity<Object> updateGenerationById(@PathVariable("plantId") UUID plantId, @PathVariable("generationId") UUID generationId, @RequestBody @Valid CreateGenerationDto createGenerationDto){
        var plantModel = plantService.findById(plantId);
        if (!plantService.findById(plantId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
        var generationModel = generationService.findById(generationId);
        if (!generationService.findById(generationId).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Generation not found");
        }
        BeanUtils.copyProperties(createGenerationDto, generationModel.get());
        generationModel.get().setPlant(plantModel.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(generationService.save(generationModel.get()));
    }

    @GetMapping("/generations-last-12-months")
    public ResponseEntity<Object> getGenerationsLast12Months(){
        LocalDateTime startDate = LocalDateTime.now().minusMonths(11).withDayOfMonth(1);
        LocalDateTime endDate = LocalDateTime.now();
        var generations = generationService.findAllByDate(startDate, endDate);
        var generationsDto = new ArrayList<ReadMonthGenerationsDto>();

        for (int i = 0; i<12; i++) {
            var startDateMonth = startDate.plusMonths(i);
            var endDateMonth = startDate.plusMonths(i+1).withDayOfMonth(1);
            var monthGenerations = generations.stream().filter(generation -> generation.getDate().isAfter(startDateMonth) && generation.getDate().isBefore(endDateMonth)).collect(Collectors.toList());
            var month = startDateMonth.getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault()) + "/" + startDateMonth.getYear();
            var sum = monthGenerations.stream().mapToDouble(generation -> generation.getGeneratePower()).sum();

            generationsDto.add(new ReadMonthGenerationsDto(month, sum));
        }
        return ResponseEntity.status(HttpStatus.OK).body(generationsDto);
    }
}
