package com.api.solarenergy.repositories;

import com.api.solarenergy.models.GenerationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenerationRepository extends JpaRepository<GenerationModel, UUID> {}

