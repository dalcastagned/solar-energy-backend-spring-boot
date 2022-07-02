package com.api.solarenergy.repositories;

import com.api.solarenergy.models.PlantModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlantRepository extends JpaRepository<PlantModel, UUID> {
    int countByActive(boolean active);
    @Query("SELECT p FROM PlantModel p " +
            "WHERE LOWER(p.nickname) LIKE %?1% " +
            "OR LOWER(p.place) LIKE %?1% " +
            "OR LOWER(p.brand) LIKE %?1% " +
            "OR LOWER(p.model) LIKE %?1%")
    Page<PlantModel> findAllPaginatedAndFiltered(String filter, Pageable pageable);
}
