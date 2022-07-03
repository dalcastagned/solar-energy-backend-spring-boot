package com.api.solarenergy.repositories;

import com.api.solarenergy.models.GenerationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.UUID;

public interface GenerationRepository extends JpaRepository<GenerationModel, UUID> {
    @Query(value = "SELECT * FROM generation WHERE plant_id = ?1 AND  date BETWEEN ?2 AND ?3", nativeQuery = true)
    Page<GenerationModel> findAllByPlantIdAndBetweenStartDateAndEndDate(UUID plantId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query(value = "SELECT * FROM generation WHERE plant_id = ?1 AND  date >= ?2", nativeQuery = true)
    Page<GenerationModel> findAllByPlantIdAndGreaterThanStartDate(UUID plantId, LocalDateTime startDate, Pageable pageable);

    @Query(value = "SELECT * FROM generation WHERE plant_id = ?1 AND  date <= ?2", nativeQuery = true)
    Page<GenerationModel> findAllByPlantIdAndLessThanEndDate(UUID plantId, LocalDateTime endDate, Pageable pageable);

    Page<GenerationModel> findAllByPlantId(UUID plantId, Pageable pageable);
}

