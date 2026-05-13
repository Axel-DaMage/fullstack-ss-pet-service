package com.sanosysalvos.petservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PetReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // LOST o FOUND
    private String species;
    private String color;
    private String breed;
    private Double lat;
    private Double lng;
    private String contactInfo;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters
    public Long getId() { return id; }
    public String getType() { return type; }
    public String getSpecies() { return species; }
    public String getColor() { return color; }
    public String getBreed() { return breed; }
    public Double getLat() { return lat; }
    public Double getLng() { return lng; }
    public String getContactInfo() { return contactInfo; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setType(String type) { this.type = type; }
    public void setSpecies(String species) { this.species = species; }
    public void setColor(String color) { this.color = color; }
    public void setBreed(String breed) { this.breed = breed; }
    public void setLat(Double lat) { this.lat = lat; }
    public void setLng(Double lng) { this.lng = lng; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
}
