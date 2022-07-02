package com.api.solarenergy.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

@Entity
@Table(name = "Plant")
public class PlantModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column()
    private Boolean active;
    @Column(nullable = false)
    @OneToMany(mappedBy = "plant")
    private Collection<GenerationModel> generations;

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Collection<GenerationModel> getGenerations() {
        return generations;
    }

    public void setGenerations(Collection<GenerationModel> generations) {
        this.generations = generations;
    }
}
