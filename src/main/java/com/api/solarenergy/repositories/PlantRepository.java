package com.api.solarenergy.repositories;

import com.api.solarenergy.models.PlantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlantRepository extends JpaRepository<PlantModel, UUID> {}
