package com.api.solarenergy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Generation")
public class GenerationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private Double generatePower;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "plant_id", nullable = false)
    private PlantModel plant;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getGeneratePower() {
        return generatePower;
    }

    public void setGeneratePower(Double generatePower) {
        this.generatePower = generatePower;
    }

    public PlantModel getPlant() {
        return plant;
    }

    public void setPlant(PlantModel plant) {
        this.plant = plant;
    }
}
